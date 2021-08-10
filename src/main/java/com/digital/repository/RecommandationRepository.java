package com.digital.repository;

import com.digital.entity.Recommandation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommandationRepository extends MongoRepository<Recommandation, String> {
}
