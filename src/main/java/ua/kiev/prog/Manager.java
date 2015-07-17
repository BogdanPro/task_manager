package ua.kiev.prog;

import ua.kiev.prog.database.main.entity.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Bogdan on 24.06.2015.
 */
public interface Manager {
    //user
    public void saveUser (User user);
    public List<User> listAllUsers();
    public void deleteUser (Long id);
    User loadUserByEmail(String email);
    //task
    public void saveTask (Task task);
    public List<Task> listAllTasks();
    public void deleteTask (Long id);
    Task loadTaskById(Long id);
    List<Task> listTasksByConditions(String namePattern, String descriptionPattern, Group group, User user, Date deadline);
    List<Task> listTasksByConditionsWithoutDeadline(String namePattern, String descriptionPattern, Group group, User user);
    List<Task> listTasksByNameAndDescription(String namePattern, String descriptionPattern);
    List<Task> listTasksByNameAndDescriptionAndUser(String namePattern, String descriptionPattern, User user);
    List<Task> listTasksByNameAndDescriptionAndGroup(String namePattern, String descriptionPattern, Group group);
    //group
    public void saveGroup(Group group);
    public List<Group> listAllGroups();
    public void deleteGroup (Long id);
    Group loadGroupByName(String name);
    //group
    public void saveType(Type type);
    public List<Type> listAllTypes();
    public void deleteType (Long id);
    Type loadTypeByName(String name);
    //commentary
    public void saveCommentary(Commentary commentary);
    public List<Commentary> listAllCommentaries();
    public void deleteCommentary (Long id);

}
