package ua.kiev.prog.database.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.database.main.entity.Type;

/**
 * Created by Bogdan on 01.07.2015.
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    public Type findByName(String name);
}
