package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class TransactionCreateParam {
    private String category;
    private String description;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String date;
    private Integer amount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
