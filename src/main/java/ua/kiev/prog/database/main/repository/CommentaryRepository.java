package ua.kiev.prog.database.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.database.main.entity.Commentary;

/**
 * Created by Bogdan on 14.07.2015.
 */
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
