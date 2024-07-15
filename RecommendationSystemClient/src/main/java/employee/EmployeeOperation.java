/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import services.EmployeeService;
import services.Interfaces.IEmployeeService;
import services.Interfaces.IProfileService;
import services.Interfaces.IUserActivityService;
import services.Interfaces.IUserNotificationService;
import services.ProfileService;
import services.UserActivityService;
import services.UserNotificationsService;

/**
 *
 * @author ria.mishra
 */
public class EmployeeOperation {
    private final Client client;
    public EmployeeOperation(Client client) {
        this.client = client;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. View rolled out items");
            System.out.println("2. Submit feedback");
            System.out.println("3. View notifications");
            System.out.println("4. Update your profile");
            System.out.println("5. logout");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        IEmployeeService employeeService = new EmployeeService(client);
        IUserActivityService userActivityService = new UserActivityService(client);
        IUserNotificationService userNotificationService = new UserNotificationsService(client);
        IProfileService profileService = new ProfileService(client);
        switch (input) {
            case 1:
                employeeService.viewRolledOutItems();
                break;
            case 2:
                employeeService.submitFeedback();
                break; 
            case 3:
                userNotificationService.viewNotifications();
                break; 
            case 4:
                profileService.updateProfile();
                break;
            case 5:
                userActivityService.addUserActivity();
                System.exit(1);
            default:
                break;
        }
    }
}
