package ir.expensetracker.api;

public class ReminderCreateResult {
    private Integer reminderId;

    public ReminderCreateResult(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    @Override
    public String toString() {
        return "{" +
                "reminderId=" + reminderId +
                '}';
    }
}
