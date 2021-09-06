package com.digital.repository;

import com.digital.entity.ReponseClient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReponseClientRepository  extends MongoRepository<ReponseClient, String> {

    ReponseClient findById(ObjectId id);
    Optional<ReponseClient> findByIdUserAndIdQuestion(String IdUser, String IdQuestion);
}
