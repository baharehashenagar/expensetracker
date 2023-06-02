package ir.expensetracker.api;

public class BudgetGoalOfUserParam {
    private String date;
    private Integer userId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
