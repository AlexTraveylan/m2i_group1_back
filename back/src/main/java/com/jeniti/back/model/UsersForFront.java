package com.jeniti.back.model;

import com.jeniti.back.entity.Channel;
import lombok.Data;

import javax.persistence.*;

@Data
public class UsersForFront {

    private Long id;

    private String username;

    private Boolean isLogged;

    private Channel current_channel;
}
