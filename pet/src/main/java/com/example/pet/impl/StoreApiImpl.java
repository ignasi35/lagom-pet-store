package com.example.pet.impl;

import akka.NotUsed;
import com.example.pet.api.Order;
import com.example.pet.api.StoreApi;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

import javax.inject.Singleton;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Singleton
public class StoreApiImpl implements StoreApi {

    @Override
    public ServiceCall<NotUsed, NotUsed> deleteOrder(long orderId) {
        System.out.println("deleteOrder");
        return notUsed -> completedFuture(NotUsed.getInstance());
    }

    @Override
    public ServiceCall<NotUsed, PMap<String, Integer>> getInventory() {
        System.out.println("getInventory");
        return notUsed -> completedFuture(HashTreePMap.empty());
    }

    @Override
    public ServiceCall<NotUsed, Order> getOrderById(long orderId) {
        System.out.println("getOrderById");
        return notUsed -> completedFuture(Order.builder().build());
    }

    @Override
    public ServiceCall<Order, Order> placeOrder() {
        System.out.println("placeOrder");
        return order -> completedFuture(Order.builder().build());
    }

}