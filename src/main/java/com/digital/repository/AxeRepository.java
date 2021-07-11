package com.digital.repository;

import com.digital.entity.Axe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AxeRepository extends MongoRepository<Axe, String> {
}
