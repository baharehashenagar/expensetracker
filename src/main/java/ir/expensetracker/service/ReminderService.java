package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.authentication.JWTUtil;
import ir.expensetracker.entity.RemindersEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.repository.IRemindersRepository;
import ir.expensetracker.service.facade.IReminderService;
import ir.expensetracker.service.facade.IValidatorService;
import ir.expensetracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService implements IReminderService {
    private IRemindersRepository remindersRepository;
    private IValidatorService validatorService;

    @Autowired
    public ReminderService(IRemindersRepository remindersRepository, IValidatorService validatorService) {
        this.remindersRepository = remindersRepository;
        this.validatorService = validatorService;
    }

    @Override
    @Transactional
    public ReminderCreateResult createReminder(ReminderCreateParam param, String jwt) {
        UserEntity user = validatorService.validateUserExistence(JWTUtil.getUserIdFromToken(jwt));
        if (param.getDescription() == null || param.getDescription().equals("")) {
            throw new InvalidParameterException("Description is empty");
        }
        if (param.getDueDate() == null || param.getDueDate().equals("") || DateUtil.toDate(param.getDueDate()) == null) {
            throw new InvalidParameterException("DueDate is empty or it has incorrect format");
        }
        RemindersEntity reminder = new RemindersEntity();
        reminder.setUser(user);
        reminder.setDescription(param.getDescription());
        reminder.setDueDate(DateUtil.toDate(param.getDueDate()));
        reminder = remindersRepository.save(reminder);
        return new ReminderCreateResult(reminder.getId());
    }

    @Override
    @Transactional
    public ReminderDeleteResult deleteReminder(ReminderDeleteParam param, String jwt) {
        Optional<RemindersEntity> reminder = remindersRepository.findById(param.getReminderId());
        if (reminder.isPresent()) {
            remindersRepository.deleteById(param.getReminderId());
            return new ReminderDeleteResult(true);
        } else {
            throw new RecordNotFoundException("Invalid ReminderId");
        }
    }

    @Override
    @Transactional
    public List<RemindersOfUserResult> findUserRemindersForSpecificDate(String date, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        if (date == null || date.equals("") || DateUtil.toDate(date) == null) {
            throw new InvalidParameterException("Date is empty");
        }
        List<RemindersEntity> remindersList = remindersRepository.findUserRemindersForSpecificDate(userId, DateUtil.toDate(date));
        List<RemindersOfUserResult> result = remindersList.stream().map(r -> new RemindersOfUserResult(r.getId(), r.getDescription(), DateUtil.fromDate(r.getDueDate()))).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public List<RemindersOfUserResult> findUserReminders(String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        List<RemindersEntity> remindersList = remindersRepository.findUserReminders(userId);
        List<RemindersOfUserResult> result = remindersList.stream().map(r -> new RemindersOfUserResult(r.getId(), r.getDescription(), DateUtil.fromDate(r.getDueDate()))).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public List<AllRemindersResult> findAllRemindersForSpecificDate(String date) {
        if (date == null || date.equals("") || DateUtil.toDate(date) == null) {
            throw new InvalidParameterException("Date is empty");
        }
        List<RemindersEntity> remindersList = remindersRepository.findAllRemindersForSpecificDate(DateUtil.toDate(date));
        List<AllRemindersResult> result = remindersList.stream().map(r -> new AllRemindersResult(r.getDescription(),
                r.getUser().getUsername(), r.getUser().getMobileNumber())).collect(Collectors.toList());
        return result;
    }
}
