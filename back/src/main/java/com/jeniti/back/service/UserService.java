package com.jeniti.back.service;

import com.jeniti.back.entity.User_class;
import com.jeniti.back.repository.IUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class UserService {

    @Autowired
    private IUserRepository uRepository;


    public User_class UpdateUser(User_class user) {
        return uRepository.save(user);
    }

    public Optional<User_class> getUser(final Long id) {
        return uRepository.findById(id);
    }

    public Iterable<User_class> getAllUsers() { return uRepository.findAll(); }


    public void deleteUser(final Long id) {
        User_class user = uRepository.findById(id).orElse(null);
        uRepository.deleteMessageByUser(user);
        uRepository.deleteById(id);
    }

}
