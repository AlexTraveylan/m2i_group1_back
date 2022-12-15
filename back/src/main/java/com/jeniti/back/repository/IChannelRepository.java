package com.jeniti.back.repository;

import com.jeniti.back.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChannelRepository extends JpaRepository<Channel, Long> {
}
