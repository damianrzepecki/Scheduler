package pl.sda.scheduler.users;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
class UserDTO {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String userName;
    @NotNull
    private String userPassword;
    @NotNull
    private Role role;
}
