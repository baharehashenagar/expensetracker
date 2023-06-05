package ir.expensetracker.api;

public class AllTransactionsParam {
    private Integer transactionId;

    public AllTransactionsParam(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "AllTransactionsParam{" +
                "transactionId=" + transactionId +
                '}';
    }
}
