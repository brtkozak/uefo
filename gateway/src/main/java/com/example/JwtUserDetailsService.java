package com.example;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        try {
            UserRecord user = auth.getUserByEmail(username);
            if(user != null){
                return new User(user.getEmail(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}