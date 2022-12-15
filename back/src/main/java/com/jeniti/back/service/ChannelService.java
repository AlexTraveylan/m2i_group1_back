package com.jeniti.back.service;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.repository.IChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    @Autowired
    public IChannelRepository cRepository;

    public Channel createChannel(Channel channel) {
        return cRepository.save(channel);
    }

    public Iterable<Channel> getAllChannel() {
        return cRepository.findAll();
    }

}
