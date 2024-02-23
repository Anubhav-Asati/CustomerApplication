package com.example.test.service;

import com.example.test.dto.UserRequest;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest userRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


}
