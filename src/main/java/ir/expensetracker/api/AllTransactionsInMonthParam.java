package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class AllTransactionsInMonthParam {
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String fromDate;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String toDate;

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

    @Override
    public String toString() {
        return "{" +
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
