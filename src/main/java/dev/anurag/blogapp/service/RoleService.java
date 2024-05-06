package dev.anurag.blogapp.service;

import dev.anurag.blogapp.entity.Role;
import dev.anurag.blogapp.entity.User;
import dev.anurag.blogapp.repository.RoleRepository;
import dev.anurag.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;
    public void saveUser(Role role) {
        role.assignId(sequenceGenerator);
        roleRepository.save(role);
    }
}
