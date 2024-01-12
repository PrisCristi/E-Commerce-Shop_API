package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.dto.UserRegistrationRequest;
import com.ecomerceApi.Priscila.dto.UserRegistrationResponse;
import com.ecomerceApi.Priscila.exception.UserExistsExecption;
import com.ecomerceApi.Priscila.exception.UserNotFoundException;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService; // security library

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);// optional annotation is explicitly handling the case where User might not be found
        if (optionalUser.isPresent())
            return optionalUser.get();
        else {
            throw new UserNotFoundException("Email not found.");
        }
    }
    public boolean isEmailValid(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        return optionalUser.isPresent();
    }

    public UserRegistrationResponse response(UserRegistrationRequest request) throws UserExistsExecption {
        if (isEmailValid(request.getEmail())) {
            throw new UserExistsExecption("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null; // not sure about this null return.
    }
}
