package com.jeniti.back.service;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.repository.IChannelRepository;
import com.jeniti.back.repository.IMessageRepository;
import com.jeniti.back.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private IChannelRepository cRepository;
    @Autowired
    private IUserRepository uRepository;
    @Autowired
    IMessageRepository mRepository;

    public Channel createChannel(Channel channel) {
        return cRepository.save(channel);
    }

    public Iterable<Channel> getAllChannel() {
        return cRepository.findAll();
    }

    public void deleteChannel(long id) {
        Channel c = cRepository.findById(id).orElse(null);
        Channel g = cRepository.findById(1L).orElse(null);
        uRepository.setChannelGeneral(c, g);
        mRepository.deleteMessageByChannel_id(c);
        cRepository.deleteById(id);
    }

    public Optional<Channel> getByIdChannel(long id) {
        return cRepository.findById(id);
    }

    public Channel updateChannel(Channel c) {
        return cRepository.save(c);
    }
}
