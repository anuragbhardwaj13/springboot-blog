package dev.anurag.blogapp.repository;

import dev.anurag.blogapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {
User findUserByUsername(String username);
User findUserByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
