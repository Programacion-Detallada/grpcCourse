package com.schools.api.services;

import com.schools.api.entities.Schools;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SchoolsService {
    CompletableFuture<Schools> save(Schools school);
    CompletableFuture<Schools> getOneById(Long id);
    CompletableFuture<List<Schools>> getAll();
    CompletableFuture<Schools> update(Schools school, Long id);
    CompletableFuture<Schools> delete(Long id);
}
