package com.getjavajob.common;


import com.getjavajob.common.enums.AccountRelationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "accountsrelations", uniqueConstraints = {@UniqueConstraint(columnNames = {"sender_id", "recipient_id"})})
@RequiredArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Account sender;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Account recipient;
    @NonNull
    @Enumerated(EnumType.STRING)
    private AccountRelationStatus relation;
}
