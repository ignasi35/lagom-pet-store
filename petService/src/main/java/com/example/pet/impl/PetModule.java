package com.example.pet.impl;

import com.example.pet.PetserviceApi;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class PetModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(PetserviceApi.class, PetserviceImpl.class);
    }
}
