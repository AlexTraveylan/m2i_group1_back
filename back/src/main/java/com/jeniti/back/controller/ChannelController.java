package com.jeniti.back.controller;

import com.jeniti.back.entity.Channel;
import com.jeniti.back.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity deleteChannel (@PathVariable long id) {
        if (id != 1) {
            Optional<Channel> channel = cService.getByIdChannel(id);
            if (channel.isPresent()) {
                cService.deleteChannel(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{id}")
    public Optional<Channel> findByChannelId(@PathVariable long id)
    {return cService.getByIdChannel(id);}

    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@PathVariable Long id, @RequestBody Channel c) {
        Optional<Channel> channel = cService.getByIdChannel(id);
        if(channel.isPresent()) {
            if (1!=id) {
                Channel mChannel = channel.get();
                String cDescription = c.getDescription();
                String cName = c.getName();

                if (null != cDescription) {
                    mChannel.setDescription(cDescription);
                } else {
                    System.out.println("updateChannel description null");
                }

                if (null != cName) {
                    mChannel.setName(cName);
                } else {
                    System.out.println("updateChannel Name null");
                }

                return ResponseEntity.ok(cService.updateChannel(mChannel));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
