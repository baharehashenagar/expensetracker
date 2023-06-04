package ir.expensetracker.scheduledtask;

import ir.expensetracker.api.AllRemindersResult;
import ir.expensetracker.service.facade.IReminderService;
import ir.expensetracker.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
public class ReminderTask {

    private IReminderService reminderService;
    private static Logger logger = LogManager.getLogger(ReminderTask.class);

    @Autowired
    public ReminderTask(IReminderService reminderService){
        this.reminderService=reminderService;
    }

    @Scheduled(fixedRateString = "${REMINDER.NOTIFICATION.RATE}")
    public void scheduleTaskUsersReminders() {
        List<AllRemindersResult> remindersList=reminderService.findAllRemindersForSpecificDate(DateUtil.fromDate(new Date()));
        remindersList.stream().forEach(r->logger.info("User {} have a reminder for {}. We can alert him using text message to {} number.",r.getUsername(),r.getDescription(),r.getMobileNumber()));
    }
}
