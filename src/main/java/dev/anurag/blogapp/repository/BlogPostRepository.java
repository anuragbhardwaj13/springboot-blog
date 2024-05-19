package dev.anurag.blogapp.repository;

import dev.anurag.blogapp.entity.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BlogPostRepository extends MongoRepository<BlogPost,Long> {
    @Query("{ 'username': ?0, 'status': 'APPROVED' }")
    List<BlogPost> findAllApprovedByUserUsername(String username);
}
