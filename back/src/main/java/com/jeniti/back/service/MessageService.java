package com.jeniti.back.service;

import com.jeniti.back.entity.Message;
import com.jeniti.back.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    public IMessageRepository mRepository;

    public Message createMessage(Message m){
        return mRepository.save(m);
    }

    public Iterable<Message> getAllMessages(){
        return mRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id){
        return mRepository.findById(id);
    }

    public String deleteMessage(Long id){
        mRepository.deleteById(id);
        return "message #" + id + " deleted";
    }

    public Message updateMessage(Message m){
        return mRepository.save(m);
    }


}
