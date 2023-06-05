package ir.expensetracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_REMINDERS")
public class RemindersEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DUEDATE", nullable = false)
    private Date dueDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
