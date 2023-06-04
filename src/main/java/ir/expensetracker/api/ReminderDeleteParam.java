package ir.expensetracker.api;

public class ReminderDeleteParam {
    private Integer reminderId;

    public ReminderDeleteParam(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }
}
