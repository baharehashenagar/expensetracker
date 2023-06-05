package ir.expensetracker.api;

public class BudgetGoalOfUserParam {
    private String date;
    private Integer userId;
    private String category;

    public BudgetGoalOfUserParam(String date, Integer userId, String category) {
        this.date = date;
        this.userId = userId;
        this.category = category;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "date='" + date + '\'' +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                '}';
    }
}
