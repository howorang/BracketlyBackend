package edu.bracketly.backend.model.entity.user;

import edu.bracketly.backend.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name = "user_t")
public class User extends BaseEntity {

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails details;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
