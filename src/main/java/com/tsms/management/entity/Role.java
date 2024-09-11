package com.tsms.management.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "roles")
public class Role {
    @Id @Getter
    private String id;
    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public Role(){}
    public Role(String roleName) {
        this.roleName = roleName;
    }

}
