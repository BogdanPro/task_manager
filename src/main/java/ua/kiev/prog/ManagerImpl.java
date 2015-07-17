package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kiev.prog.database.main.entity.*;
import ua.kiev.prog.database.main.repository.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Bogdan on 24.06.2015.
 */
@Component("userService")
public class ManagerImpl implements Manager {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void saveTask(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public Task loadTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> listTasksByConditions(String namePattern, String descriptionPattern, Group group, User user, Date deadline) {
        return taskRepository.findByNameLikeAndDescriptionLikeAndGroupAndUserAndDeadline(namePattern, descriptionPattern, group, user, deadline);
    }

    @Override
    public List<Task> listTasksByConditionsWithoutDeadline(String namePattern, String descriptionPattern, Group group, User user) {
        return taskRepository.findByNameLikeAndDescriptionLikeAndGroupAndUser(namePattern, descriptionPattern, group, user);
    }

    @Override
    public List<Task> listTasksByNameAndDescription(String namePattern, String descriptionPattern) {
        return taskRepository.findByNameLikeAndDescriptionLike(namePattern, descriptionPattern);
    }

    @Override
    public List<Task> listTasksByNameAndDescriptionAndUser(String namePattern, String descriptionPattern, User user) {
        return taskRepository.findByNameLikeAndDescriptionLikeAndUser(namePattern, descriptionPattern, user);
    }

    @Override
    public List<Task> listTasksByNameAndDescriptionAndGroup(String namePattern, String descriptionPattern, Group group) {
        return taskRepository.findByNameLikeAndDescriptionLikeAndGroup(namePattern, descriptionPattern, group);
    }


    public void saveGroup(Group group) {
        groupRepository.saveAndFlush(group);
    }

    public List<Group> listAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public Group loadGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public void saveType(Type type) {
        typeRepository.saveAndFlush(type);
    }

    @Override
    public List<Type> listAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public Type loadTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public void saveCommentary(Commentary commentary) {
        commentaryRepository.saveAndFlush(commentary);
    }

    @Override
    public List<Commentary> listAllCommentaries() {
        return commentaryRepository.findAll();
    }

    @Override
    public void deleteCommentary(Long id) {
        commentaryRepository.delete(id);
    }


    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TypeRepository getTypeRepository() {
        return typeRepository;
    }

    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public void setCommentaryRepository(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public CommentaryRepository getCommentaryRepository() {
        return commentaryRepository;
    }

    public ManagerImpl() {
    }



}
