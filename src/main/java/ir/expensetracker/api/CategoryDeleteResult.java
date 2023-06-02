package ir.expensetracker.api;

public class CategoryDeleteResult {
    private boolean success;

    public CategoryDeleteResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
