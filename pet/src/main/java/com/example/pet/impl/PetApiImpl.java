package com.example.pet.impl;

import com.example.pet.api.*;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.*;

import javax.inject.Singleton;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static com.example.pet.impl.TestData.*;

@Singleton
public class PetApiImpl implements PetApi {


    public ServiceCall<Pet, NotUsed> addPet() {
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> deletePet(long petId) {
        return notUsed -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByStatus(PSequence<String> status) {
        return notUsed -> completedFuture(pets);
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByTags(PSequence<String> tags) {
        return notUsed -> completedFuture(pets);
    }

    @Override
    public ServiceCall<NotUsed, Pet> getPetById(long petId) {
        return notUsed -> completedFuture(petHorse);
    }

    @Override
    public ServiceCall<Pet, NotUsed> updatePet() {
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> updatePetWithForm(long petId) {
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, ModelApiResponse> uploadFile(long petId) {
        return notUsed -> completedFuture(modelApiResponse);
    }
}