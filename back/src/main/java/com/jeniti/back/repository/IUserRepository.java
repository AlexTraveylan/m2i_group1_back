package com.jeniti.back.repository;

import com.jeniti.back.entity.User_class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User_class, Long> {

    Optional<User_class> findByEmail(String email);

}
