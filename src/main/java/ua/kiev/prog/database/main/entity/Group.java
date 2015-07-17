package ua.kiev.prog.database.main.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bogdan on 23.06.2015.
 */
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="group", fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToMany(mappedBy="groups", fetch = FetchType.LAZY)
    private List<User> users;



    @Column
    private String name;

    @Column
    private String description;

    public Group() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<User> getUsers() {
        return users;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
