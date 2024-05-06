package dev.anurag.blogapp.service;

import dev.anurag.blogapp.entity.User;
import dev.anurag.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveUser(User user) {
        user.assignId(sequenceGenerator);
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Boolean findByUsername(String username) {
        System.out.println(userRepository.existsByUsername(username));
        return userRepository.existsByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Boolean findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
