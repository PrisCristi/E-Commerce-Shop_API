package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.controller.UserRegistrationRequest;
import com.ecomerceApi.Priscila.exception.UserExistsExecption;
import com.ecomerceApi.Priscila.exception.UserNotFoundException;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);// optional annotation is explicitly handling the case where User might not be found
        if (optionalUser.isPresent())
            return optionalUser.get();
        else {
            throw new UserNotFoundException("Email not found.");
        }
    }

    public boolean isEmailRegistered(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    public void register(UserRegistrationRequest request) throws UserExistsExecption {
        if (isEmailRegistered(request.getEmail())) {
            throw new UserExistsExecption("Email already registered");
        }

        User user = new User();
        user.setUserName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        userRepository.save(user);
    }

}
