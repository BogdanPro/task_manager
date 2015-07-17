package ua.kiev.prog.database.main.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Bogdan on 23.06.2015.
 */

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="group_id")
    private Group group;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="task")
    private List<Commentary> commentaries;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column
    private Date deadline;

    @Column
    private byte completeness;

    @ManyToOne(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name="main_task_id")
    private Task mainTask;

    @OneToMany(mappedBy="mainTask", fetch = FetchType.EAGER)
    private List<Task> subTasks;

    public Task() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setCompleteness(byte completeness) {
        this.completeness = completeness;
    }

    public void setMainTask(Task mainTask) {
        this.mainTask = mainTask;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
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

    public Group getGroup() {
        return group;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public byte getCompleteness() {
        return completeness;
    }

    public Task getMainTask() {
        return mainTask;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }
}
