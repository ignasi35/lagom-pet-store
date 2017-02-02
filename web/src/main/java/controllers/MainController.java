package controllers;

import com.example.pet.api.PetApi;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

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
            if (e instanceof NotFound) {
                return notFound("Pet with id " + id + " not found!");
            } else if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        });
    }
}
