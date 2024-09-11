package com.tsms.management.service;

import com.tsms.management.entity.User;
import com.tsms.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user){
        Optional<User> existingUser=userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("User with email '" + user.getEmail()+ "' already exists.");
        }
        else{
            return userRepository.save(user);
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
