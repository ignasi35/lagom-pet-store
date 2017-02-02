package controllers;

import com.example.pet.api.PetApi;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import org.pcollections.TreePVector;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class MainController extends Controller {

    private final PetApi petApi;

    @Inject
    public MainController(PetApi petApi) {
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
            return petApi.findPetsByStatus(TreePVector.singleton(status.get())).invoke().thenApply(pets ->
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
