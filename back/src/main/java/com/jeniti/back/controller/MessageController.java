package com.jeniti.back.controller;

import com.jeniti.back.entity.Message;
import com.jeniti.back.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService mService;

    @GetMapping
    public Iterable<Message> getAllMessages(){
        return mService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Long id){
        return mService.getMessageById(id);
    }

    @PostMapping
    public Message createMessage(@RequestBody Message m){
        return mService.createMessage(m);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id){
        return mService.deleteMessage(id);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message m){
        Optional<Message> message = mService.getMessageById(id);
        if(message.isPresent()){
            Message nMessage = message.get();
            String mContent = m.getContent();
            if(null != mContent){
                nMessage.setContent(mContent);
            }else{
                System.out.println("updateMessage content null");
            }
            return mService.updateMessage(nMessage);
        }else{
            return null;
        }




    }
}
