package com.digital.repository;


import com.digital.entity.Question;
import com.digital.entity.Reponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query("{'id':?0}")
    Optional<Question> findById(String id);

    Question getById(ObjectId id);
}


