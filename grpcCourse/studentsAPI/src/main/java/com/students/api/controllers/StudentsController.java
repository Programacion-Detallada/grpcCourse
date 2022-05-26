package com.students.api.controllers;

import com.students.api.entities.CompleteInfoDTO;
import com.students.api.entities.Students;
import com.students.api.grpcclient.SchoolsGrpcClient;
import com.students.api.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @PostMapping("")
    public CompletableFuture<ResponseEntity> postRecord(@RequestBody Students student){
        return studentsService.save(student).thenApply(ResponseEntity::ok);
    }


    @GetMapping("")
    public CompletableFuture<ResponseEntity> getAll(){
        return studentsService.getAll().thenApply(ResponseEntity::ok);
    }


    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity> getOneById(@PathVariable Long id){
        return studentsService.getOneById(id).thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity> update(@RequestBody Students student,@PathVariable Long id){
        return studentsService.update(student,id).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Long id){
        return studentsService.delete(id).thenApply(ResponseEntity::ok);
    }

    @Autowired
    SchoolsGrpcClient schoolsGrpcClient;

    @GetMapping("complete/{id}")
    public CompleteInfoDTO getStudentCompleteInfo(@PathVariable Long id){
        return schoolsGrpcClient.getCompleInfoStudent(id);
    }

}
