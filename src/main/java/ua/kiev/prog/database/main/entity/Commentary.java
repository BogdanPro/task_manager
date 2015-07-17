package ua.kiev.prog.database.main.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Bogdan on 14.07.2015.
 */
@Entity
@Table(name = "Commentaries")
public class Commentary {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="task_id")
    private Task task;

    public Commentary() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTask(ua.kiev.prog.database.main.entity.Task task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public ua.kiev.prog.database.main.entity.Task getTask() {
        return task;
    }
}
