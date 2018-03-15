package com.getjavajob.common;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "messages")
@RequiredArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Account sender;
    @NonNull
    private int recipient;
    @Column(insertable = false)
    private Date date;// TODO: 3/2/2018 localdate
    @NonNull
    private String text;
    @NonNull
    private boolean isgroup;


}