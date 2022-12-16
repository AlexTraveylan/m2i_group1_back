package com.jeniti.back.repository;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMessageRepository extends JpaRepository<Message, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.channel_id = :c")
    void deleteMessageByChannel_id(Channel c);
}
