package ir.expensetracker.api;

public class ReminderCreateParam {
    private String description;
    private String dueDate;

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
                "description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
