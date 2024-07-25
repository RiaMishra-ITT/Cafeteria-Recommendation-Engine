/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.AdminQuestions;
import models.Notification;
import models.UserResponse;
import services.Interfaces.IUserNotificationService;

/**
 *
 * @author ria.mishra
 */
public class UserNotificationsService implements IUserNotificationService {

    Client client;
    public Scanner scanner = new Scanner(System.in);

    public UserNotificationsService(Client client) {
        this.client = client;
    }

    @Override
    public void viewNotifications() {
        List<Notification> notifications;

        try {
            client.sendRequest("usernotification", Authentication.userId);
            notifications = (List<Notification>) client.receiveObjectResponse().readObject();
            System.out.println("Notification Id \tMessage\tNotification Type ID\tDate Time");
            System.out.println("-------------------------------------------------------------");
            int index = 0;
            for (Notification notification : notifications) {
                index++;
                System.out.println(index + "\t"
                        + notification.message + "\t"
                        + notification.notificationTypeId + "\t"
                        + notification.dateTime);

            }

            System.out.println("Select notification to view details");
            int notificationId = scanner.nextInt();
            scanner.nextLine();
            int notificationTypeId = notifications.get(notificationId - 1).notificationTypeId;
            if (notificationTypeId == 4) {
                this.submitUserResponse(notifications, notificationId);
            } else {
                System.out.println(notifications.get(notificationId - 1).message);
            }
        } catch (IOException ex) {
            System.out.println("Client Response: Failed to convert server response");
        } catch (ClassNotFoundException ex) {
            System.out.println("Client Response: Failed to view notifications");
        }
    }

    private void submitUserResponse(List<Notification> notifications, int notificationId) {
        client.sendRequest("viewquestions", notifications.get(notificationId - 1).notificationId);
        List<AdminQuestions> questions;
        try {
            System.out.println("We are trying to improve your experience with Food Item. Please provide your feedback and help us. ");
            questions = (List<AdminQuestions>) client.receiveObjectResponse().readObject();

            List<UserResponse> responses = new ArrayList<>();
            for (AdminQuestions question : questions) {
                System.out.printf("Question.) %-10s%n", question.question);
                String answer = scanner.nextLine();
                UserResponse response = new UserResponse(0, question.questionId, answer, Authentication.userId);
                responses.add(response);
            }

            client.sendRequest("addResponse", responses);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Submitted response");
        } catch (IOException ex) {
            System.out.println("Client Response: Failed to convert server response");
        } catch (Exception ex) {
            System.out.println("Client Response: Failed to submit user response");
        }
    }

}
