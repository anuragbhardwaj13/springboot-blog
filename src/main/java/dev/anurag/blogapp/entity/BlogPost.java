package dev.anurag.blogapp.entity;

import dev.anurag.blogapp.service.SequenceGeneratorService;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "blog")
@Data
@NoArgsConstructor
public class BlogPost {
    @Id
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String Content;
    @NonNull
    private LocalDateTime date;
    public void assignId(SequenceGeneratorService sequenceGenerator) {
        this.id = sequenceGenerator.generateSequence("blog_sequence");
    }

}
