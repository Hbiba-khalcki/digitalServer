package com.digital.repository;


import com.digital.entity.Reponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReponseRepository extends MongoRepository<Reponse, String> {
}
