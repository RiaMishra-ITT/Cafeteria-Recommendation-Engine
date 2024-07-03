/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Feedback;
import models.RolledOutItem;
import services.Interfaces.IEmployeeService;

/**
 *
 * @author ria.mishra
 */
public class EmployeeService implements IEmployeeService {
    Scanner scanner = new Scanner(System.in);
    Client client;
    public EmployeeService(Client client) {
        this.client = client;
    }
    
    @Override
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
        Authentication.activities.add("Feedback submitted");
    }
    
    @Override
    public void viewRolledOutItems() {
        client.sendRequest("viewRolledOutItem", null);
        List<RolledOutItem> items;
        try {
            items = (List<RolledOutItem>) client.receiveObjectResponse().readObject();
            Set<Integer> seenIds = new HashSet<>();
            List<RolledOutItem> uniqueItems = new ArrayList<>();
            for(RolledOutItem item : items) {
                if(seenIds.add(item.menuItemId)) {
                    uniqueItems.add(item);
                }
            }
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type \t Rating \t Sentiment");
            for(RolledOutItem item : uniqueItems) {
                String mealType = item.mealTypeId == 1? "Breakfast" : item.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s \t %-10s \t %-10s%n", item.menuItemId,item.itemName, item.price, item.availbilityStatus, mealType,item.rating,item.sentiment);

            }
            Authentication.activities.add("View rolled out items");
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
