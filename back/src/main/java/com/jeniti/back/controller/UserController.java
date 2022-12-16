package com.jeniti.back.controller;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.service.ChannelService;
import com.jeniti.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService uService;

    @Autowired
    private ChannelService cService;

    @GetMapping
    public Iterable<User_class> getAllUsers() {
        return uService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User_class getUserById(@PathVariable("id") final long id) {
        Optional<User_class> u =  uService.getUser(id);
        if (u.isPresent()) {
            return u.get();
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public User_class updateUser(@PathVariable("id") final long id, @RequestBody User_class user) {
        Optional<User_class> u = uService.getUser(id);
        if (u.isPresent()) {
            User_class currentUser = u.get();

            String email = user.getEmail();
            if (email != null) {
                currentUser.setEmail(email);
            }

            String password = user.getPassword();
            if (password != null) {
                currentUser.setPassword(passwordEncoder.encode(password));
            }

            String username = user.getUsername();
            ;
            if (username != null) {
                currentUser.setUsername(username);
            }

            Channel current_channel = user.getCurrent_channel();
            if (current_channel != null && current_channel.getId() != null) {
                Long current_channel_id = current_channel.getId();
                Optional<Channel> c = cService.getByIdChannel(current_channel_id);
                if (c.isPresent()) {
                    currentUser.setCurrent_channel(c.get());
                }
            }

            return uService.UpdateUser(currentUser);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") final long id) {
        uService.deleteUser(id);
    }
}