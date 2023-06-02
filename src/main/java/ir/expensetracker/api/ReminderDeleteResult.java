package ir.expensetracker.api;

public class ReminderDeleteResult {
    private boolean success;

    public ReminderDeleteResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
