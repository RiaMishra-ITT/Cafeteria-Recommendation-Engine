/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.recommendationsystemclient.Client;
import java.time.LocalDate;
import java.util.Scanner;
import models.Feedback;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public class EmployeeService {
    Scanner scanner = new Scanner(System.in);
    Client client;
    public EmployeeService(Client client) {
        this.client = client;
    }
    
    public void submitFeedback() {
        MenuItemService menuItemService = new MenuItemService(client);
        menuItemService.viewAllItems();
        System.out.println("Enter the item id against which you want to submit feedback");
        int mealItemeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Add Ratings");
        String rating = scanner.nextLine();
        System.out.println("Add Comment");
        String comment = scanner.nextLine();
        
        LocalDate currentDate = LocalDate.now();
        Feedback feedback = new Feedback(0,comment,rating,currentDate.toString(),mealItemeId,2);
       
        client.sendRequest("submitFeedback", feedback);
        String response = (String) client.receiveResponse();
        System.out.println("Server Response: " + response);
    }
}
