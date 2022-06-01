package com.letscode.apiuser.domain;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {

    private String name;
    private String userName;
    private String email;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;

    public UserResponse(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.userName = userEntity.getUserName();
        this.email = userEntity.getEmail();
        this.creationDate = userEntity.getCreationDate();
        this.updateDate = userEntity.getUpdateDate();
    }
}
