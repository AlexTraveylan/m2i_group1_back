package com.jeniti.back.controller;

import com.jeniti.back.entity.Message;
import com.jeniti.back.entity.User_class;
import com.jeniti.back.service.MessageService;
import com.jeniti.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService mService;

    @Autowired
    private UserService uService;

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
        Optional<User_class> u = uService.getUser(m.getUser_id().getId());

        if (u.isPresent()) {
            User_class currentUser = u.get();
            m.setUser_id(currentUser);
            m.setChannel_id(currentUser.getCurrent_channel());
            return mService.createMessage(m);
        } else {
            return null;
        }
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
