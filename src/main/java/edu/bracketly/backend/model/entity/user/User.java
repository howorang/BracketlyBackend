package edu.bracketly.backend.model.entity.user;

import edu.bracketly.backend.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends BaseEntity {
    private String username;

    private String password;

    private long rank;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
