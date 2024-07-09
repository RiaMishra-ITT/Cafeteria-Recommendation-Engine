/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Repositories.AdminQuestionsRepository;
import customexception.FailedToAddNotification;
import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.AdminQuestions;
import services.Interfaces.IUserNotificationService;
import services.Interfaces.IAdminQuestionsService;

/**
 *
 * @author ria.mishra
 */
public class AdminQuestionsService implements IAdminQuestionsService{
    private final AdminQuestionsRepository repository;
    private final IUserNotificationService notificationService;

    public AdminQuestionsService() throws UnableToConnectDatabase {
        this.repository = new AdminQuestionsRepository();
        this.notificationService = new UserNotificationService();
    }
    
    @Override
    public void addQuestions(ObjectInputStream input) throws IOException, ClassNotFoundException{
        List<AdminQuestions> questions = (List<AdminQuestions>) input.readObject();
        try {
            repository.addQuestions(questions);
            Set<Integer> distinctFoodItemIds = questions.stream()
                             .map(question -> question.menuItemId)
                             .collect(Collectors.toSet());
            notificationService.sendDetailFeedbackNotification(distinctFoodItemIds);
        } catch (SQLException | FailedToAddNotification ex) {
            Logger.getLogger(AdminQuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
