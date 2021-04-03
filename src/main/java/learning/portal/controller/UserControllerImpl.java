package learning.portal.controller;

import learning.portal.model.User;
import learning.portal.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class UserControllerImpl implements UserController{

    @Autowired
    UserRepository userRepository;

    @Override
    public Collection<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(user ->
                userList.add(new User(user.getEmployeeID(),user.getFullName(),user.getEmailID(),user.getUserName(),
                        user.getContact(),user.getUserType())));
         return  userList;
    }

    @Override
    public boolean addUser(@RequestBody User user) {
        try{
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            log.error(e);
            return false;
        }

    }

    @Override
    public boolean updateUserByID(@PathVariable Long employeeID, @RequestBody User user) {
        Optional<User> user1 = userRepository.findById(employeeID);
        if(user1.isPresent()) {
            if(user1.get().getEmployeeID().equals(user.getEmployeeID())){
                try{
                    userRepository.save(user);
                    return true;
                }
                catch (Exception e){
                    log.error(e);
                    return false;
                }
            }
            else {
                log.info("Incorrect ID");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(@PathVariable Long employeeID) {
        if(userRepository.findById(employeeID).isPresent()) {
            try{
                userRepository.deleteById(employeeID);
                return true;
            }
            catch (Exception e){
                log.error(e);
                return false;
            }
        }
        return false;
    }
}
