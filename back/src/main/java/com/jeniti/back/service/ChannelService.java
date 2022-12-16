package com.jeniti.back.service;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.repository.IChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void deleteChannel(long id) {
        cRepository.deleteById(id);
    }

    public Optional<Channel> getByIdChannel(long id) {
        return cRepository.findById(id);
    }
}
