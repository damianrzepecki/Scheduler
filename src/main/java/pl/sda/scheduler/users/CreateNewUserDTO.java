package pl.sda.scheduler.users;

import lombok.Data;

@Data
class CreateNewUserDTO {
    private String userName;
    private String userPassword;
    private Role role;
}
