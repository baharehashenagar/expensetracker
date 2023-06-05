package ir.expensetracker.api;

import io.swagger.annotations.ApiModelProperty;

public class ReminderCreateParam {
    private String description;
    @ApiModelProperty(example = "yyyy-MM-dd")
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
