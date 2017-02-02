package com.example.pet.impl;

import com.example.pet.api.*;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import java.util.Optional;

public class TestData {

    public static Category category = new Category(some(new Long(1l)), some("some-category"));

    public static Pet petHorse = new Pet(
            some(new Long(1l)),
            some(category),
            "horse",
            TreePVector.empty(),
            TreePVector.empty(),
            some(Pet.StatusEnum.AVAILABLE));
    public static Pet petDog = new Pet(
            some(new Long(2l)),
            some(category),
            "dog",
            TreePVector.empty(),
            TreePVector.empty(),
            some(Pet.StatusEnum.PENDING));
    public static Pet petCat = new Pet(
            some(new Long(3l)),
            some(category),
            "cat",
            TreePVector.empty(),
            TreePVector.empty(),
            some(Pet.StatusEnum.SOLD));

    public static PSequence<Pet> pets = TreePVector.<Pet>empty()
            .plus(petHorse)
            .plus(petDog)
            .plus(petCat);

    public static ModelApiResponse modelApiResponse = new ModelApiResponse(
            some(new Integer(1)),
            some("some-type"),
            some("some-message")
    );

    private static <T> Optional<T> some(T value) {
        return Optional.of(value);
    }
}
