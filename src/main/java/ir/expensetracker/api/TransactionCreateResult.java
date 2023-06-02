package ir.expensetracker.api;

public class TransactionCreateResult {
    private Integer transacionId;
    private String alert;

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
