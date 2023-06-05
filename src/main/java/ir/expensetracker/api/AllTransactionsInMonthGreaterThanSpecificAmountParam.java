package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class AllTransactionsInMonthGreaterThanSpecificAmountParam {
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String fromDate;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String toDate;
    private Integer amount;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
