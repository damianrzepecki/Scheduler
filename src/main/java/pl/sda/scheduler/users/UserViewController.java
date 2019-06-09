package pl.sda.scheduler.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/admin")
public class UserViewController {
    private UserService userService;
    private UserMapper userMapper;

    UserViewController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String getUserList(Model model, Pageable pageable) {
        Page<UserDTO> userPage = userService.getAllUsers(pageable).map(userMapper::DTO);
        model.addAttribute("page", userPage);
        model.addAttribute("users", userPage.getContent());
        return "admin/admins";
//    }
//    @PostMapping("/save")
//    String saveUser(CreateNewUserDTO createNewUserDTO){
//        userMapper.DTO(userService.addNewUser)
//    }
    }
}