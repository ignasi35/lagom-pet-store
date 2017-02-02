package com.example.pet.impl;

import akka.NotUsed;
import com.example.pet.api.User;
import com.example.pet.api.UserApi;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.PSequence;

import javax.inject.Singleton;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Singleton
public class UserApiImpl implements UserApi {


    public ServiceCall<User, NotUsed> createUser() {
        System.out.println("createUser");
        return user -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<PSequence<User>, NotUsed> createUsersWithArrayInput() {
        System.out.println("createUsersWithArrayInput");
        return user -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<PSequence<User>, NotUsed> createUsersWithListInput() {
        System.out.println("createUsersWithListInput");
        return users -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> deleteUser(String username) {
        System.out.println("deleteUser");
        return notUsed -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, User> getUserByName(String username) {
        System.out.println("getUserByName");
        return notUsed -> completedFuture(User.builder().build());
    }

    @Override
    public ServiceCall<NotUsed, String> loginUser(String username, String password) {
        System.out.println("loginUser");
        return notUsed -> completedFuture(username);
    }


    public ServiceCall<NotUsed, NotUsed> logoutUser() {
        System.out.println("logoutUser");
        return notUsed -> completedFuture(NotUsed.getInstance());
    }


    public ServiceCall<User, NotUsed> updateUser(String username) {
        System.out.println("updateUser");
        return user -> completedFuture(NotUsed.getInstance());
    }
}