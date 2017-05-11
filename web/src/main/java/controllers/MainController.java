package controllers;

import com.example.pet.PetStatusEnum;
import com.example.pet.PetserviceApi;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import org.pcollections.TreePVector;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import scala.compat.java8.OptionConverters.*;

public class MainController extends Controller {

    private final PetserviceApi petApi;

    @Inject
    public MainController(PetserviceApi petApi) {
        this.petApi = petApi;
    }

    public CompletionStage<Result> showPet(long id) {
        return petApi.getPetById(id).invoke().thenApply(pet ->
                ok(views.html.pet.render(pet))
        ).exceptionally(e -> {
            RuntimeException re = unwrap(e);
            if (re instanceof NotFound) {
                return notFound("Pet with id " + id + " not found!");
            }
            throw re;
        });
        
    }

    public CompletionStage<Result> petsByStatus(Option<String> status) {
        if (status.isEmpty()) {
            return CompletableFuture.completedFuture(ok(views.html.pets.render(TreePVector.empty())));
        } else {
            Optional<String> jStatus = Optional.ofNullable(status.getOrElse(null));
            return petApi.findPetsByStatus(TreePVector.singleton(jStatus.map(PetStatusEnum::valueOf).get())).invoke().thenApply(pets ->
                    ok(views.html.pets.render(pets))
            );
        }
    }

    private RuntimeException unwrap(Throwable throwable) {
        if (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            return unwrap(throwable.getCause());
        }
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        return new RuntimeException(throwable);
    }
}
