package com.digital.repository;

import com.digital.entity.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationRepository  extends MongoRepository<Evaluation, String> {
}
