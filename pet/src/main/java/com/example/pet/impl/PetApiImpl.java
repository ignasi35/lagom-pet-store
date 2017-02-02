package com.example.pet.impl;

import com.example.pet.api.*;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import org.pcollections.*;

import javax.inject.Singleton;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
        return notUsed -> {
            List<Pet> thePets = pets
                    .stream()
                    .filter(pet -> pet.getTags().stream().filter(tag -> tag.getName().map(n -> tags.contains(n)).orElse(false)).collect(Collectors.toList()).size() > 0)
                    .collect(Collectors.toList());
            if (thePets.size() == 0)
                throw new NotFound("No pet found for provided tags.");
            else
                return CompletableFuture.completedFuture(TreePVector.from(thePets));
        };
    }

    @Override
    public ServiceCall<NotUsed, Pet> getPetById(long petId) {
        return notUsed -> {
            List<Pet> thePet = pets
                    .stream()
                    .filter(pet -> pet.getId().map(id -> id.equals(petId)).orElse(false))
                    .collect(Collectors.toList());
            if (thePet.size() == 0)
                throw new NotFound("Pet not found");
            else
                return CompletableFuture.completedFuture(thePet.get(0));
        };
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