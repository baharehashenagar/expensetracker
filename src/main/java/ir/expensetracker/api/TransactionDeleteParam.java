package ir.expensetracker.api;

public class TransactionDeleteParam {
    private Integer transactionId;

    public TransactionDeleteParam(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
