package ua.kiev.prog.database.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.database.main.entity.Group;

/**
 * Created by Bogdan on 23.06.2015.
 */
public interface GroupRepository extends JpaRepository<Group, Long>{
    public Group findByName(String name);
}
