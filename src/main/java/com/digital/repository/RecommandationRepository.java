package com.digital.repository;

import com.digital.entity.Axe;
import com.digital.entity.Question;
import com.digital.entity.Recommandation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface RecommandationRepository extends MongoRepository<Recommandation, String> {

    @Query("{'id':?0}")
    Optional<Recommandation> findById(String id);
}
