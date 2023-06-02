package ir.expensetracker.service.facade;

import ir.expensetracker.api.ReminderCreateParam;
import ir.expensetracker.api.ReminderCreateResult;
import ir.expensetracker.api.ReminderDeleteParam;
import ir.expensetracker.api.ReminderDeleteResult;

public interface IReminderService {

    public ReminderCreateResult createReminder(ReminderCreateParam param);
    public ReminderDeleteResult deleteReminder(ReminderDeleteParam param);
}
