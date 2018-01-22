package com.anteastra.devopsbuddy.backend.persistence.repositories;

import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}