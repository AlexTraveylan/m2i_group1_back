package com.jeniti.back.config;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.repository.IChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;

@Configuration
@Transactional
public class DataBaseInitializer implements CommandLineRunner {
    @Autowired
    private IChannelRepository channelRepository;

    @Override
    public void run(String... args) throws Exception {
        Channel channel = new Channel();
        channel.setId(1L);
        channel.setName("Général");
        channel.setDescription("Canal de tous et surtout de toutes!");
        channelRepository.save(channel);
    }
}
