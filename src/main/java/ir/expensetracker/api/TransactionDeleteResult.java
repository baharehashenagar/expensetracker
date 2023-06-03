package ir.expensetracker.api;

public class TransactionDeleteResult {
    private boolean success;

    public TransactionDeleteResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
