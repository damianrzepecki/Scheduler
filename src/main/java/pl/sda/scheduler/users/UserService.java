package pl.sda.scheduler.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    Page<User>  getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    User addNewUser(User user){
        return userRepository.save(user);
    }
}
