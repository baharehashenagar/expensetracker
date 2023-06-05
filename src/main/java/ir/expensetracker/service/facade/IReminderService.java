package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

import java.util.List;

public interface IReminderService {

    public ReminderCreateResult createReminder(ReminderCreateParam param, String jwt);

    public ReminderDeleteResult deleteReminder(ReminderDeleteParam param, String jwt);

    public List<RemindersOfUserResult> findUserRemindersForSpecificDate(String date, String jwt);

    public List<RemindersOfUserResult> findUserReminders(String jwt);

    public List<AllRemindersResult> findAllRemindersForSpecificDate(String date);
}
