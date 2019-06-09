package pl.sda.scheduler.users;

import org.springframework.stereotype.Component;

@Component
class UserMapper {
    User model(CreateNewUserDTO createNewUserDTO){
        User user = new User();
        user.setUserName(createNewUserDTO.getUserName());
        user.setUserPassword(createNewUserDTO.getUserPassword());
        user.setRole(createNewUserDTO.getRole());
        return user;
    }
    UserDTO DTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPassword(user.getUserPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
