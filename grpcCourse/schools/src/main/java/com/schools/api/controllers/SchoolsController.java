package com.schools.api.controllers;

import com.schools.api.entities.Schools;
import com.schools.api.services.SchoolsService;
import com.schools.api.servicesimpl.SchoolsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/schools")
public class SchoolsController {
    @Autowired
    private SchoolsService schoolsService;

    @PostMapping("")
    public CompletableFuture<ResponseEntity> postRecord(@RequestBody Schools school){
        return schoolsService.save(school).thenApply(ResponseEntity::ok);
    }

    @GetMapping("")
    public CompletableFuture<ResponseEntity> getAll(){
        return schoolsService.getAll().thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity> update(@RequestBody Schools school,@PathVariable Long id){
        return schoolsService.update(school,id).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Long id){
        return schoolsService.delete(id).thenApply(ResponseEntity::ok);
    }
}
