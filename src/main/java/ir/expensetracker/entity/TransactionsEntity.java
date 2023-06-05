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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CATEGORYID", referencedColumnName = "Id", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "Id", nullable = false)
    private UserEntity user;

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
        return "{" +
                "id=" + id +
                ", category=" + category +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}
