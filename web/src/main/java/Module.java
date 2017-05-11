import com.example.pet.PetserviceApi;
import com.example.pet.StoreserviceApi;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.api.ServiceAcl;
import com.lightbend.lagom.javadsl.api.ServiceInfo;
import com.lightbend.lagom.javadsl.client.ServiceClientGuiceSupport;

public class Module extends AbstractModule implements ServiceClientGuiceSupport {

    @Override
    protected void configure() {
        bindClient(PetserviceApi.class);
        bindClient(StoreserviceApi.class);

        bindServiceInfo(ServiceInfo.of("online-pet-store", ServiceAcl.path("/web/*")));
    }

}
