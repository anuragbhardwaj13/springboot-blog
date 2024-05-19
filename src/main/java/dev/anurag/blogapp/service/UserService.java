package dev.anurag.blogapp.service;

import dev.anurag.blogapp.entity.User;
import dev.anurag.blogapp.repository.UserRepository;
import dev.anurag.blogapp.secuity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void updateUser(User user) {
        // Ensure the user exists
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User Not Found with id: " + user.getId());
        }
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return user;
    }
    public Boolean findByUsername(String username) {
        System.out.println(userRepository.existsByUsername(username));
        return userRepository.existsByUsername(username);
    }
    public UserDetails loadUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Boolean findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
