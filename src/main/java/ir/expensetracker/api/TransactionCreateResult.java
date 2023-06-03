package ir.expensetracker.api;

public class TransactionCreateResult {
    private Integer transacionId;
    private String alert;

    public TransactionCreateResult(Integer transacionId, String alert) {
        this.transacionId = transacionId;
        this.alert = alert;
    }

    public Integer getTransacionId() {
        return transacionId;
    }

    public void setTransacionId(Integer transacionId) {
        this.transacionId = transacionId;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
