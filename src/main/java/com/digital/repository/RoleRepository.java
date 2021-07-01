package com.digital.repository;

import java.util.Optional;

import com.digital.entity.ERole;
import com.digital.entity.Role;


import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

