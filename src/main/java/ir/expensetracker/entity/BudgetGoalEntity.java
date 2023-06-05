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
    @JoinColumn(name = "CATEGORYID", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "MAXAMOUNT", nullable = false)
    private Integer maxAmount;

    @Column(name = "BUDGETDATE", nullable = false)
    private Date budgetDate;

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

    public Date getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(Date budgetDate) {
        this.budgetDate = budgetDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", category=" + category +
                ", user=" + user +
                ", maxAmount=" + maxAmount +
                ", budgetDate=" + budgetDate +
                '}';
    }
}
