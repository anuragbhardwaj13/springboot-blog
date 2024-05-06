package dev.anurag.blogapp.entity;

import dev.anurag.blogapp.service.SequenceGeneratorService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private long id;

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>(); // Embedded roles

    @DBRef
    List<BlogPost> blogPosts=new ArrayList<>();

    public void assignId(SequenceGeneratorService sequenceGenerator) {
        this.id = sequenceGenerator.generateSequence("user_sequence");
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
