package com.digital.repository;

import com.digital.entity.Axe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;


public interface AxeRepository extends MongoRepository<Axe, String> {
    @Query("{'id':?0}")
    public Optional<Axe> findById(String id);
}
