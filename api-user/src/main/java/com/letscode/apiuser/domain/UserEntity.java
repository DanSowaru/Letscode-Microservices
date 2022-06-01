package com.letscode.apiuser.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String name;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;
    private String email;

    public UserEntity(String name, String userName, String email, ZonedDateTime creationDate, ZonedDateTime updateDate) {
        this.userName = userName;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.email = email;
    }
}



