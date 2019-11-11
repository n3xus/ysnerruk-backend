package com.ysnerruk.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.model.Role;
import com.ysnerruk.api.repository.UserRepository;
import com.ysnerruk.api.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new ApiException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles = new ArrayList<Role>();
            roles.add(Role.ROLE_CLIENT);
            user.setRoles(roles);
            userRepository.save(user);
            return jwtProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new ApiException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ApiException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtProvider.getUsername(jwtProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}