package com.students.api.grpcclient;

import com.schoolsservicegrpc.grpc.SchoolObject;
import com.schoolsservicegrpc.grpc.id;
import com.schoolsservicegrpc.grpc.schoolServiceGrpc;
import com.students.api.entities.CompleteInfoDTO;
import com.students.api.entities.School;
import com.students.api.entities.Students;
import com.students.api.repositories.StudentsRepository;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolsGrpcClient {

    @Autowired
    private StudentsRepository studentsRepository;

    ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9090").usePlaintext().build();

    public CompleteInfoDTO getCompleInfoStudent(Long id){
        Students student = studentsRepository.getById(id);
        schoolServiceGrpc.schoolServiceBlockingStub stub = schoolServiceGrpc.newBlockingStub(channel);
        SchoolObject retrievedSchool =  stub.getOneByID(com.schoolsservicegrpc.grpc.id.newBuilder().setId(student.getSchoolId()).build());
        channel.shutdown();

        School school = new School();
        school.setId(retrievedSchool.getId());
        school.setName(retrievedSchool.getName());
        school.setLocation(retrievedSchool.getLocation());
        school.setStatus(retrievedSchool.getStatus());


        CompleteInfoDTO completeInfo = new CompleteInfoDTO();
        completeInfo.setId(student.getId());
        completeInfo.setName(student.getName());
        completeInfo.setLastName(student.getLastName());
        completeInfo.setSchool(school);
        completeInfo.setStatus(student.getStatus());

        return completeInfo;
    }
}
