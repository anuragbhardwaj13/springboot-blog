package dev.anurag.blogapp.entity;

import dev.anurag.blogapp.service.SequenceGeneratorService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
public class Role {

    @Id
    private long id;

    @NonNull
    private RoleEnum name;
    public Role() {

    }

    public Role(RoleEnum name) {
        this.name = name;
    }



    public void assignId(SequenceGeneratorService sequenceGenerator) {
        this.id = sequenceGenerator.generateSequence("role_sequence");
    }


}
