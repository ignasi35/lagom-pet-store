import com.example.pet.api.PetApi;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.client.ServiceClientGuiceSupport;

public class Module extends AbstractModule implements ServiceClientGuiceSupport {

    @Override
    protected void configure() {
        bindClient(PetApi.class);
    }
}
