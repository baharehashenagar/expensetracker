package ir.expensetracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_TRANSACTIONS")
public class TransactionsEntity {

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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRANSACTIONDATE", nullable = false)
    private Date transactionDate;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionsEntity{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}
