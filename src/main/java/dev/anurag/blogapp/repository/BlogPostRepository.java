package dev.anurag.blogapp.repository;

import dev.anurag.blogapp.entity.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostRepository extends MongoRepository<BlogPost,Long> {
}
