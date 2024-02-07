package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.request_responseModels.UserRegistrationRequest;
import com.ecomerceApi.Priscila.exception.UserExistsException;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

    }

    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void register(UserRegistrationRequest request) throws UserExistsException {
        if (isEmailRegistered(request.getEmail())) {
            throw new UserExistsException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }


    public String login (String username, String password) {
        User user = userRepository.findByEmail(username)
                .orElseThrow( ()-> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())){
            GrantedAuthority authorities = new SimpleGrantedAuthority(
                    user.getRoles().name());
            authorities.add(authorities);

            }


            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(String.valueOf(user.getRole()));


        }

    }

    /*   public String login(String username, String password) {
        return userRepository.findByEmail(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtService.generateToken(
                                user,
                                Map.of("auth", user.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toList())
                                        600000000)
                        )
                )
                .orElseThrow();

    }

  */

}
