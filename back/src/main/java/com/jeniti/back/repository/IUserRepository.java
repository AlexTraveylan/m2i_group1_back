package com.jeniti.back.repository;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.User_class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User_class, Long> {

    Optional<User_class> findByEmail(String email);

//    @Query("SELECT u FROM User_class u WHERE u.sessionID = :uuid")
    Optional<User_class> findBySessionId(String uuid);

    @Transactional
    @Modifying
    @Query("update User_class u SET u.current_channel = :g WHERE u.current_channel = :c")
    void setChannelGeneral(Channel c, Channel g);


    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.user_id = :u")
    void deleteMessageByUser(User_class u);


}
