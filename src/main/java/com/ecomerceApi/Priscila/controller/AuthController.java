package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.Payload.response.JwtResponse;
import com.ecomerceApi.Priscila.Payload.response.MessageResponse;
import com.ecomerceApi.Priscila.Payload.request.LoginRequest;
import com.ecomerceApi.Priscila.Payload.request.SignupRequest;
import com.ecomerceApi.Priscila.model.ERole;
import com.ecomerceApi.Priscila.model.Role;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.RoleRepository;
import com.ecomerceApi.Priscila.repository.UserRepository;
import com.ecomerceApi.Priscila.security.JwtUtils;
import com.ecomerceApi.Priscila.service.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        // authentic user by user using name and password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

       // generate token and save them.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // save user details into a String list.
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List <String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

       // return http response and user info and auth.
        return  ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles)
        );

    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){

        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already used"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already registered"));
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> stringRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        //System.out.println(stringRoles);

        if (stringRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RuntimeException("Role not found"));
            roles.add(userRole);
        } else {
            stringRoles.forEach(role ->{
                switch (role){
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("Role is not found"));
                        roles.add(adminRole);

                        break;

                    case "ROLE_COSTUMER":
                        Role customerRole = roleRepository.findByName(ERole.ROLE_COSTUMER)
                                .orElseThrow(()-> new RuntimeException("Role not found"));
                        roles.add(customerRole);
                        break;

                    default:
                      List <Role> findRole = roleRepository.findAll();
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException("Role not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
