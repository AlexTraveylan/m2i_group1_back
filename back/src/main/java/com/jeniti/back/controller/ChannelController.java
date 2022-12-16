package com.jeniti.back.controller;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @DeleteMapping("/{id}")
    public void deleteChannel (@PathVariable long id) {
        if (id != 1) {
            cService.deleteChannel(id);}
        }


    @GetMapping("/{id}")
    public Optional<Channel> findByChannelId(@PathVariable long id)
    {return cService.getByIdChannel(id);}

    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable Long id, @RequestBody Channel c) {
        Optional<Channel> channel = cService.getByIdChannel(id);
        if(channel.isPresent()) {
            Channel mChannel = channel.get();
            String cDescription = c.getDescription();
            String cName = c.getName();

            if (null != cDescription) {
                mChannel.setDescription(cDescription);
            }else {
                System.out.println("updateChannel description null");
            }

            if (null != cName) {
                mChannel.setName(cName);
            }else {
                System.out.println("updateChannel Name null");
            }

            return cService.updateChannel(mChannel);
        }else {
            return null;
        }

    }

}
