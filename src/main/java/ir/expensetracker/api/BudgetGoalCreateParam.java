package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class BudgetGoalCreateParam {
    private String category;
    private Integer maxAmount;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String fromDate;
    @ApiModelProperty(example = "yyyy-MM-dd")
    private String toDate;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
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

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                ", maxAmount=" + maxAmount +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
