package ua.kiev.prog.database.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.database.main.entity.User;

/**
 * Created by Bogdan on 23.06.2015.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);
}
