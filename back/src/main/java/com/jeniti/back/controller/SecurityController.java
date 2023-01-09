package com.jeniti.back.controller;


import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.model.UserModel;
import com.jeniti.back.repository.IUserRepository;
import com.jeniti.back.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ChannelService cService;

    @PostMapping("/register")
    public User_class register(@RequestBody User_class user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Channel channel = cService.getByIdChannel(1).get();
        user.setCurrent_channel(channel);
        user.setIsLogged(false);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserModel userModel) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
        Optional<User_class> c = userRepository.findByEmail(userModel.getEmail());
        if (c.isPresent()) {
            User_class currentUser = c.get();
            currentUser.setIsLogged(true);
            userRepository.save(currentUser);
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logOut")
    public ResponseEntity logout(@RequestBody User_class user) {
        Optional<User_class> u = userRepository.findById(user.getId());
        if (u.isPresent()) {
            User_class currentUser = u.get();
            currentUser.setIsLogged(false);
            userRepository.save(currentUser);
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
