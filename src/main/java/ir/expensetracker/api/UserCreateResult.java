package ir.expensetracker.api;

public class UserCreateResult {
    private Integer userId;

    public UserCreateResult(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
