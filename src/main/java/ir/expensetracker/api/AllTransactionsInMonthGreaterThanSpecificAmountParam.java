package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class AllTransactionsInMonthGreaterThanSpecificAmountParam {
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String date;
    private Integer amount;

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
                " date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
