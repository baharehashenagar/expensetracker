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
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private Integer userId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "BudgetGoalEntity{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", maxAmount=" + maxAmount +
                ", budgetDate=" + budgetDate +
                '}';
    }
}
