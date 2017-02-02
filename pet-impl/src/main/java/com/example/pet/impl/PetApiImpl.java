package com.example.pet.impl;

import akka.NotUsed;
import com.example.pet.api.*;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.*;

import javax.inject.Singleton;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Singleton
public class PetApiImpl implements PetApi {


    public ServiceCall<Pet, NotUsed> addPet() {
        System.out.println("addPet");
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> deletePet(long petId) {
        System.out.println("deletePet");
        return notUsed -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByStatus(PSequence<String> status) {
        System.out.println("findPetsByStatus");
        return notUsed -> completedFuture(TreePVector.empty());
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByTags(PSequence<String> tags) {
        System.out.println("findPetsByTags");
        return notUsed -> completedFuture(TreePVector.empty());
    }

    @Override
    public ServiceCall<NotUsed, Pet> getPetById(long petId) {
        System.out.println("getPetById");
        return notUsed -> completedFuture(Pet.builder("name").build());
    }

    @Override
    public ServiceCall<Pet, NotUsed> updatePet() {
        System.out.println("updatePet");
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> updatePetWithForm(long petId) {
        System.out.println("updatePetWithForm");
        return pet -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, ModelApiResponse> uploadFile(long petId) {
        System.out.println("uploadFile");
        return notUsed -> completedFuture(ModelApiResponse);
    }
}