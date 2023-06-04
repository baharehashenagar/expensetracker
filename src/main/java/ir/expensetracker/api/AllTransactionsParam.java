package ir.expensetracker.api;

public class AllTransactionsParam {
    private Integer userId;

    public AllTransactionsParam(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
