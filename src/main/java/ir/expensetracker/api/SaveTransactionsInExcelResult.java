package ir.expensetracker.api;

public class SaveTransactionsInExcelResult {
    private boolean success;

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
