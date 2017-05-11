package com.example.pet.impl;

import akka.Done;
import akka.NotUsed;
import com.example.pet.Pet;
import com.example.pet.PetStatusEnum;
import com.example.pet.PetserviceApi;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.example.pet.impl.TestData.modelApiResponse;
import static com.example.pet.impl.TestData.pets;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Singleton
public class PetserviceImpl implements PetserviceApi {

    public ServiceCall<Pet, Done> addPet() {
        return pet -> completedFuture(Done.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, Done> deletePet(long petId) {
        return notUsed -> completedFuture(Done.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByStatus(PSequence<PetStatusEnum> status) {
        return notUsed -> {
            List<Pet> thePets = pets
                    .stream()
                    .filter(pet -> pet.getPetStatus().isPresent() && status.contains(pet.getPetStatus().get().name().toLowerCase()))
                    .collect(Collectors.toList());
            if(thePets.size() == 0)
                throw new NotFound("No pet found for provided status.");
            else
                return CompletableFuture.completedFuture(TreePVector.from(thePets));
        };
    }


    @Override
    public ServiceCall<NotUsed, PSequence<Pet>> findPetsByTags(PSequence<String> tags) {
        return notUsed -> {
            List<Pet> thePets = pets
                    .stream()
                    .filter(pet -> pet.getTags().stream().filter(tag -> tags.contains(tag.getName())).collect(Collectors.toList()).size() > 0)
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
    public ServiceCall<Pet, Done> updatePet() {
        return pet -> completedFuture(Done.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, Done> updatePetWithForm(long petId) {
        return pet -> completedFuture(Done.getInstance());
    }


}