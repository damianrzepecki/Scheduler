package pl.sda.scheduler.users;

import org.mapstruct.Mapper;

@Mapper
public abstract class UserMapper {
    public abstract UserDTO userToUserDTO(User user);
    public abstract User userDTOToUser(UserDTO userDTO);

}
