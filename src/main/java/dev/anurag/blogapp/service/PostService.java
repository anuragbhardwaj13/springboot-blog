package dev.anurag.blogapp.service;

import dev.anurag.blogapp.entity.BlogPost;
import dev.anurag.blogapp.entity.User;
import dev.anurag.blogapp.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostService {
    @Autowired
    private BlogPostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;
    @Transactional
    public void saveEntry(BlogPost post, String userName) {
        try {
            // Retrieve the existing user by username
            User user = userService.findUserByUsername(userName);

            // Set the current date and time for the blog post
            post.setDate(LocalDateTime.now());

            post.assignId(sequenceGenerator);
            post.setStatus("PENDING");
            post.setUsername(userName);
            // Save the blog post
            BlogPost newEntry = postRepository.save(post);

            // Add the new blog post to the user's list of blog posts
            user.getBlogPosts().add(newEntry);

            // Save the updated user
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }@Transactional
    public void updateEntry(BlogPost post, String userName) {
        try {
            // Retrieve the existing user by username
            User user = userService.findUserByUsername(userName);

            // Set the current date and time for the blog post
            post.setDate(LocalDateTime.now());

//            post.assignId(sequenceGenerator);
            post.setStatus("PENDING");
            post.setUsername(userName);
            // Save the blog post
            BlogPost newEntry = postRepository.save(post);

            // Add the new blog post to the user's list of blog posts
            user.getBlogPosts().add(newEntry);

            // Save the updated user
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


    public List<BlogPost> getAllEntries() {
        return postRepository.findAll();
    }

    public BlogPost getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    public List<BlogPost> findAllApprovedByUserUsername(String username) {
        return postRepository.findAllApprovedByUserUsername(username);
    }
    public BlogPost approvePost(String postId) {
        BlogPost post = postRepository.findById(Long.valueOf(postId)).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus("APPROVED");
        return postRepository.save(post);
    }

}
