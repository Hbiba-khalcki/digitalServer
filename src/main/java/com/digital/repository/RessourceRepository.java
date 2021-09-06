package com.digital.repository;
import com.digital.entity.Ressource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RessourceRepository  extends MongoRepository<Ressource, String> {
    public Optional<Ressource> findById(String _id);
}
