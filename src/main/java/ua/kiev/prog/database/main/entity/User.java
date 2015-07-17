package ua.kiev.prog.database.main.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bogdan on 23.06.2015.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="user")
    private List<Commentary> commentaries;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id")
    private Type type;

    @ManyToMany
    @JoinTable(name="User_Group",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}
    )
    private List<Group> groups;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<Task> tasks;

    @Column
    private String name;

    @Column
    private String function;

    @Column
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column
    private String description;

    @Column
    private String password;

    @Column
    private String salt;

    public User() {
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {

        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public long getId() {
        return id;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}
