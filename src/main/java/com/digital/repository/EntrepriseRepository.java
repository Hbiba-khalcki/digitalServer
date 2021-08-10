package com.digital.repository;

import com.digital.entity.Entreprise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntrepriseRepository  extends MongoRepository<Entreprise, String> {
}
