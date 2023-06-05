package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class BudgetGoalOfUserParam {
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String fromDate;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String toDate;
    private String category;

    public BudgetGoalOfUserParam(String fromDate, String toDate, String category) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.category = category;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
