package pl.sda.scheduler.users;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/admin")
public class UserController {
    private UserService userService;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserList(Model model, Pageable pageable) {
        Page<UserDTO> userPage = userService.getAllUsers(pageable).map(userMapper::userToUserDTO);
        model.addAttribute("page", userPage);
        model.addAttribute("users", userPage.getContent());
        return "admin/admins";
    }
    @PostMapping("/admin/save")
    String saveUser(UserDTO userDTO){
        userService.addNewUser(userMapper.userDTOToUser(userDTO));
        return "redirect/app/client"; 
    }

}