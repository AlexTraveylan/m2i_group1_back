package com.jeniti.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @Column( updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String updated_at;

    @OneToOne
    @JoinColumn(name = "channel_id")
    @Column(nullable = false)
    private Channel channel_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User_class user_id;
}
