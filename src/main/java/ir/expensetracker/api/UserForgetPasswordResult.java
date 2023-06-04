package ir.expensetracker.api;

public class UserForgetPasswordResult {
    private boolean success;

    public UserForgetPasswordResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "{" +
                "success=" + success +
                '}';
    }
}
