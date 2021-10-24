package repository;

import entity.UserCredentials;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import javax.transaction.Transaction;
import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<UserCredentials, String> {
    UserCredentials findByUserName(String username);
}
