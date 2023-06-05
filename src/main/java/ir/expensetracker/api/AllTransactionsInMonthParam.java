package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class AllTransactionsInMonthParam {
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "date='" + date + '\'' +
                '}';
    }
}
