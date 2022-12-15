package com.jeniti.back.controller;


import com.jeniti.back.entity.Channel;
import com.jeniti.back.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChannelController {

    @Autowired
    private ChannelService cService;

    @GetMapping
    public Iterable<Channel> getAllChannel() {
        return cService.getAllChannel();
    }

    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        return cService.createChannel(channel);
    }
}
