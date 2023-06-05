package ir.expensetracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_BUDGETGOAL")
public class BudgetGoalEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID", referencedColumnName = "Id", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "Id", nullable = false)
    private UserEntity user;

    @Column(name = "MAXAMOUNT", nullable = false)
    private Integer maxAmount;

    @Column(name = "FROMDATE", nullable = false)
    private Date fromDate;

    @Column(name = "TODATE", nullable = false)
    private Date toDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", category=" + category +
                ", user=" + user +
                ", maxAmount=" + maxAmount +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
