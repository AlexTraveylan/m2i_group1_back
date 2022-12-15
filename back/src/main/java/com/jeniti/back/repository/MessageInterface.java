package com.jeniti.back.repository;

import com.jeniti.back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageInterface extends JpaRepository<Message, Long> {
}
