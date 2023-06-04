package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

import java.util.List;

public interface IReminderService {

    public ReminderCreateResult createReminder(ReminderCreateParam param);
    public ReminderDeleteResult deleteReminder(ReminderDeleteParam param);
    public List<RemindersOfUserResult> findUserRemindersForSpecificDate(Integer userId, String date);
    public List<RemindersOfUserResult> findUserReminders(Integer userId);
    public List<AllRemindersResult> findAllRemindersForSpecificDate(String date);
}
