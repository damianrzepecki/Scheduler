package pl.sda.scheduler.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class User {
    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String userPassword;
    private Role role;
}
