package com.jeniti.back.controller;


import com.jeniti.back.entity.Channel;
import com.jeniti.back.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels")
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
