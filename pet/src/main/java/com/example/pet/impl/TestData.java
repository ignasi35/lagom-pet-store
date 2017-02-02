package com.example.pet.impl;

import com.example.pet.api.*;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import java.util.Optional;

public class TestData {

    public static Category category = new Category(some(new Long(1l)), some("some-category"));

    public static Tag awesomeTag = new Tag(Optional.of(1L), Optional.of("awesome"));
    public static Tag homey = new Tag(Optional.of(2L), Optional.of("homey"));
    public static Tag grumpy = new Tag(Optional.of(3L), Optional.of("grumpy"));

    public static Pet petHorse = new Pet(
            some(new Long(1l)),
            some(category),
            "horse",
            TreePVector.<String>empty().plus("https://www.scienceabc.com/wp-content/uploads/2016/05/horse-running.jpg"),
            TreePVector.<Tag>empty().plus(awesomeTag),
            some(Pet.StatusEnum.AVAILABLE));
    public static Pet petDog = new Pet(
            some(new Long(2l)),
            some(category),
            "dog",
            TreePVector.<String>empty().plus("https://sites.psu.edu/siowfa16/files/2016/10/dog3-17woz29.jpg"),
            TreePVector.<Tag>empty().plus(awesomeTag).plus(homey),
            some(Pet.StatusEnum.PENDING));
    public static Pet petCat = new Pet(
            some(new Long(3l)),
            some(category),
            "cat",
            TreePVector.<String>empty().plus("https://scontent.cdninstagram.com/t51.2885-15/e35/15802625_1799473900326589_5948450424158683136_n.jpg?ig_cache_key=MTQxNzg5OTU0NDcyNDc3ODc0Mg%3D%3D.2"),
            TreePVector.<Tag>empty().plus(grumpy).plus(homey),
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
