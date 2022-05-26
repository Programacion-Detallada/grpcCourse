package com.students.api.servicesimpl;

import com.students.api.entities.Students;
import com.students.api.repositories.StudentsRepository;
import com.students.api.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Students> save(Students student) {
        student.setStatus(1);
        return CompletableFuture.completedFuture(studentsRepository.save(student));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Students> getOneById(Long id) {
        Optional<Students> student = studentsRepository.findById(id);
        return CompletableFuture.completedFuture(student.orElse(null));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<Students>> getAll() {
        return CompletableFuture.completedFuture(studentsRepository.findAll());
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Students> update(Students student, Long id) {
        Students findStudent = studentsRepository.getById(id);
        if(findStudent.getId() != null){
            ModelMapper modelMapper = new ModelMapper();
            Students updateStudent = modelMapper.map(findStudent,Students.class);
            return CompletableFuture.completedFuture(studentsRepository.save(updateStudent));
        }else {
            return null;
        }
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Students> delete(Long id) {
        Students findStudent = studentsRepository.getById(id);
        if(findStudent.getId() != null){
            findStudent.setStatus(0);
            return CompletableFuture.completedFuture(studentsRepository.save(findStudent));
        }else {
            return null;
        }
    }
}
