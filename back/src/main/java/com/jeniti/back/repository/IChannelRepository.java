package com.jeniti.back.repository;

import com.jeniti.back.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IChannelRepository extends JpaRepository<Channel, Long> {
}
