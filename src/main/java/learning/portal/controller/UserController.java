package learning.portal.controller;

import learning.portal.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public interface UserController {
    @GetMapping
    Collection<User> getAllUsers();

    @PostMapping
    boolean addUser(User user);

    @PutMapping("/{employeeID}")
    boolean updateUserByID(Long employeeID, User user);

    @DeleteMapping("/{employeeID}")
    boolean deleteByID(Long employeeID);

}
