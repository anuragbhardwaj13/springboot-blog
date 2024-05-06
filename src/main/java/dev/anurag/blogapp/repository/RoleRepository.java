package dev.anurag.blogapp.repository;

import dev.anurag.blogapp.entity.RoleEnum;
import dev.anurag.blogapp.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum name);

}
