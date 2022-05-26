package com.students.api.services;

import com.students.api.entities.Students;

import java.util.List;
import java.util.concurrent.CompletableFuture;
public interface StudentsService {
    CompletableFuture<Students> save(Students student);
    CompletableFuture<Students> getOneById(Long id);
    CompletableFuture<List<Students>> getAll();
    CompletableFuture<Students> update(Students student, Long id);
    CompletableFuture<Students> delete(Long id);
}
