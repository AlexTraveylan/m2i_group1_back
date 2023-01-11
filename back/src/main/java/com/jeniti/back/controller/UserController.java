package com.jeniti.back.controller;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.model.UsersForFront;
import com.jeniti.back.service.ChannelService;
import com.jeniti.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<UsersForFront> getAllUsers() {
        Iterable<User_class> allUsers = uService.getAllUsers();
        List<UsersForFront>  usersOnChannel = new ArrayList<>();

        allUsers.forEach(user -> {
                usersOnChannel.add(new UsersForFront(user.getId(), user.getUsername(), user.getIsLogged(), user.getCurrent_channel()));
        });

        return usersOnChannel;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User_class> getUserById(@PathVariable("id") final long id) {
        Optional<User_class> u =  uService.getUser(id);
        if (u.isPresent()) {
            return ResponseEntity.ok(u.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/{id}/{sessionID}")
    public ResponseEntity<User_class> getUserBySessionID(@PathVariable("sessionID") final String sessionID, @PathVariable("id") final long id) {
        Optional<User_class> u = uService.getUserBySessionID(sessionID);
        if (u.isPresent()) {
            User_class currentUser = u.get();
            return ResponseEntity.ok(currentUser);
        } else {
            System.out.println("not present");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/channel/{channelId}")
    public List<UsersForFront> getUsersbyChannelId(@PathVariable("channelId") final long channelId) {
        Iterable<User_class> allUsers = uService.getAllUsers();
        List<UsersForFront>  usersOnChannel = new ArrayList<>();

        allUsers.forEach(user -> {
            if (user.getCurrent_channel().getId() == channelId) {
                usersOnChannel.add(new UsersForFront(user.getId(), user.getUsername(), user.getIsLogged(), user.getCurrent_channel()));
            }
        });
        return usersOnChannel;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User_class> updateUser(@PathVariable("id") final long id, @RequestBody User_class user) {
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

            Boolean isLogged = user.getIsLogged();
            if (isLogged != null) {
                currentUser.setIsLogged(isLogged);
            }

            Channel current_channel = user.getCurrent_channel();
            if (current_channel != null) {
                if (current_channel.getId() != null) {
                    Long current_channel_id = current_channel.getId();
                    Optional<Channel> c = cService.getByIdChannel(current_channel_id);
                    if (c.isPresent()) {
                        currentUser.setCurrent_channel(c.get());
                    }
                } else {
                    ResponseEntity.badRequest().build();
                }
            }
            return ResponseEntity.ok(uService.UpdateUser(currentUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") final long id) {
        Optional<User_class> u = uService.getUser(id);
        if (u.isPresent()) {
            uService.deleteUser(id);
            return ResponseEntity.ok("L'utilisateur n°" + id + " a bien été supprimé");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}