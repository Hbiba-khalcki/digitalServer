package com.digital.repository;


import com.digital.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query("{'id':?0}")
    public Optional<Question> findById(String id);
}


