package ir.expensetracker.api;

public class CategoryCreateResult {
    private boolean success;

    public CategoryCreateResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
