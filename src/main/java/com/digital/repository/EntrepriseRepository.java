package com.digital.repository;

import com.digital.entity.Entreprise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntrepriseRepository  extends MongoRepository<Entreprise, String> {

    Optional<Entreprise> findByUserId(String userId);


}
