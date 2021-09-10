package com.digital.repository;
import com.digital.entity.Axe;
import com.digital.entity.Ressource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface RessourceRepository  extends MongoRepository<Ressource, String> {

    @Query("{'id':?0}")
    public Optional<Ressource> findById(String id);
}
