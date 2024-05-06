package dev.anurag.blogapp.secuity.service;

import dev.anurag.blogapp.entity.User;
import dev.anurag.blogapp.repository.UserRepository;
import dev.anurag.blogapp.secuity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByUsername(username);
        if(user!=null){
            return UserDetailsImpl.build(user);        }
        throw new UsernameNotFoundException("user not found with given username");
    }


}
