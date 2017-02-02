package com.example.pet.impl;

import com.example.pet.api.PetApi;
import com.example.pet.api.StoreApi;
import com.example.pet.api.UserApi;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class PetModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(PetApi.class, PetApiImpl.class),
                serviceBinding(StoreApi.class, StoreApiImpl.class),
                serviceBinding(UserApi.class, UserApiImpl.class)
        );
    }
}
