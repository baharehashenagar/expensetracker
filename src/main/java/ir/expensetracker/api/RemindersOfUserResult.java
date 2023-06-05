package ir.expensetracker.api;

public class RemindersOfUserResult {
    private Integer reminderId;
    private String description;
    private String dueDate;

    public RemindersOfUserResult(Integer reminderId, String description, String dueDate) {
        this.reminderId = reminderId;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "{" +
                "reminderId=" + reminderId +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
