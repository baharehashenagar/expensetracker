package ir.expensetracker.api;

public class UserChangePasswordResult {
    private boolean success;

    public UserChangePasswordResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
