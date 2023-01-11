package com.jeniti.back.model;

import com.jeniti.back.entity.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class UsersForFront {

    private Long id;

    private String username;

    private Boolean isLogged;

    private Channel current_channel;
}
