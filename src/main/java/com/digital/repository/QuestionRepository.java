package com.digital.repository;


import com.digital.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public Optional<Question> findById(String _id);
}


