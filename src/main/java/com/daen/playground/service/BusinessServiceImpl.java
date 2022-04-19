package com.daen.playground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService{

    @Autowired
    TestGenerator generator;

    private ExecutorService executor = null;

    @Override
    public String requestOtherInst(String codes) {
        String result = null;

        ExecutorService executor = Executors.newFixedThreadPool(10);
        this.executor = executor;

        String allFutures = Arrays.stream(codes.split(","))
                                    .parallel()
                                    .map(this::createFuture)
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.joining(", "));
//        String[] allCode = codes.split(",");
//        int size = codes.split(",").length;
//        List<CompletableFuture<String>> futures = new ArrayList<>();
//        for ( int i = 0; i < size; i++ ) {
//            futures.add(createFuture(allCode[i], executor));
//        }

//        futures.stream().forEach(v -> {
//            System.out.println(v);
//        });

        String x = allFutures;

//        try {
//            x = CompletableFuture.allOf(allFutures).thenApply(whenFinish -> {
//                return Arrays.stream(allFutures).map(CompletableFuture::join).collect(Collectors.joining(", "));
//            }).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        return x;
    }

    private CompletableFuture<String> createFuture(String code) {

        System.out.println(this.executor.toString());

        return CompletableFuture.supplyAsync(() -> generator.testGeneratorWithTime(code).toString(), this.executor);
    }

}
