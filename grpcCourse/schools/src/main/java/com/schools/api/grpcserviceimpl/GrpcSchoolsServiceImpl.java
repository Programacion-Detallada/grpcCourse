package com.schools.api.grpcserviceimpl;

import com.schools.api.entities.Schools;
import com.schools.api.services.SchoolsService;
import com.schools.api.servicesimpl.SchoolsServiceImpl;
import com.schoolsservicegrpc.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@GrpcService
public class GrpcSchoolsServiceImpl extends schoolServiceGrpc.schoolServiceImplBase {
    @Autowired
    private SchoolsService schoolsService;

    @Override
    public void getOneByID(id request, StreamObserver<SchoolObject> responseObserver) {
        CompletableFuture<Schools> foundSchool = schoolsService.getOneById(request.getId());
        foundSchool.join();
        SchoolObject objectSchoolToReturn = SchoolObject.newBuilder()
                .setId(foundSchool.join().getId())
                .setLocation(foundSchool.join().getLocation())
                .setName(foundSchool.join().getName())
                .setStatus(foundSchool.join().getStatus())
                .build();
        responseObserver.onNext(objectSchoolToReturn);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request, StreamObserver<SchoolsList> responseObserver) {
        CompletableFuture<List<Schools>> completeList = schoolsService.getAll();
        List<SchoolObject> schoolsGrpcList = new ArrayList<>();
        for(int i =0; i<completeList.join().size(); i++){
            SchoolObject schoolToadd = SchoolObject.newBuilder()
                    .setId(completeList.join().get(i).getId())
                    .setLocation(completeList.join().get(i).getLocation())
                    .setName(completeList.join().get(i).getName())
                    .setStatus(completeList.join().get(i).getStatus())
                    .build();

            schoolsGrpcList.add(schoolToadd);
        }
        SchoolsList.Builder listToReturn = SchoolsList.newBuilder();

        for(SchoolObject eachSchool: schoolsGrpcList){
            listToReturn.addSchool(eachSchool);
        }
        responseObserver.onNext(listToReturn.build());
        responseObserver.onCompleted();

    }
}
