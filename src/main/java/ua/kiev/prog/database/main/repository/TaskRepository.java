package ua.kiev.prog.database.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.database.main.entity.Group;
import ua.kiev.prog.database.main.entity.Task;
import ua.kiev.prog.database.main.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by Bogdan on 23.06.2015.
 */
public interface TaskRepository extends JpaRepository<Task, Long>{
    public Task findById(Long id);
    public List<Task> findByNameLikeAndDescriptionLikeAndGroupAndUserAndDeadline (String namePattern, String descriptionPattern, Group group, User user, Date deadline);
    public List<Task> findByNameLikeAndDescriptionLikeAndGroupAndUser (String namePattern, String descriptionPattern, Group group, User user);
    public List<Task> findByNameLikeAndDescriptionLikeAndGroup(String namePattern, String descriptionPattern, Group group);
    public List<Task> findByNameLikeAndDescriptionLikeAndUser(String namePattern, String descriptionPattern, User user);
    public List<Task> findByNameLikeAndDescriptionLike(String namePattern, String descriptionPattern);
}
