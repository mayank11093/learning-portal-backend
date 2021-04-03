package learning.portal.repository;

import learning.portal.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
    Optional<User> findByEmployeeIDAndPassword(Long employeeID, String password);
}
