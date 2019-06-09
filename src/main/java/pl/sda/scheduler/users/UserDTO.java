package pl.sda.scheduler.users;

import lombok.Data;

@Data
class UserDTO {
    private long id;
    private String userName;
    private String userPassword;
    private Role role;
}
