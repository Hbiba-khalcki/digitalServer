package com.digital.repository;


import com.digital.entity.Reponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReponseRepository extends MongoRepository<Reponse, String> {
    List<Reponse>  findByQstId(String qstId);

    Reponse getById(ObjectId id);
}
