package com.jeniti.back.controller;

import com.jeniti.back.entity.Message;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.service.MessageService;
import com.jeniti.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService mService;

    @Autowired
    private UserService uService;

    @GetMapping
    public ResponseEntity<Iterable<Message>> getAllMessages(){
        return ResponseEntity.ok(mService.getAllMessages());
    }

    @GetMapping("/channel/{channelId}")
    public ResponseEntity getMessageByChannelId(@PathVariable Long channelId) {
        Iterable<Message> allMessages = mService.getAllMessages();
        List<Message> messageOnChannel = new ArrayList<>();
        allMessages.forEach((message) -> {
            if (message.getChannel_id().getId() == channelId) {
                messageOnChannel.add(message);
            }
        });
        return ResponseEntity.ok(messageOnChannel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(mService.getMessageById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message m) {

        if (m.getUser_id() != null && m.getUser_id().getId() != null) {
            Optional<User_class> u = uService.getUser(m.getUser_id().getId());
            if (u.isPresent()) {
                User_class currentUser = u.get();
                m.setUser_id(currentUser);
                m.setChannel_id(currentUser.getCurrent_channel());
                return ResponseEntity.ok(mService.createMessage(m));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id){
       try{
           mService.deleteMessage(mService.getMessageById(id).getId());
           return ResponseEntity.ok().build();
       }catch(Exception e){
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message m){
        try{
            Message nMessage = mService.getMessageById(id);
            String mContent = m.getContent();
                if(null != mContent) {
                    nMessage.setContent(mContent);
                    return ResponseEntity.ok(mService.updateMessage(nMessage));
                }else{
                    return ResponseEntity.notFound().build();
                }
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
