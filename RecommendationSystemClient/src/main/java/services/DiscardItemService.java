/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AdminQuestions;
import models.DiscardItemInfo;
import services.Interfaces.IDiscardItemService;
import services.Interfaces.IMenuItemService;

public class DiscardItemService implements IDiscardItemService{
    Client client;
    public DiscardItemService(Client client) {
        this.client = client;
    }
    
    @Override
    public void viewAllDiscardItems() {
        client.sendRequest("viewDiscardItems", null);
        IMenuItemService menuItemService = new MenuItemService(client);
        List<DiscardItemInfo> items;
        try {
            items = (List<DiscardItemInfo>) client.receiveObjectResponse().readObject();
            if(items.isEmpty()) {
                System.out.println("No items found");
                return;
            }
            for(DiscardItemInfo item : items) {
                System.out.print("Food Item - ");
                System.out.println(item.itemName);
                
                System.out.print("Average Rating - ");
                System.out.println(item.rating);
                
                System.out.print("Sentiments - ");
                System.out.println(item.sentiment);

            }
        
            System.out.println("1)Remove the food items from menu");
            System.out.println("2) Get detailed feedback");
            Scanner scanner = new Scanner(System.in);
            int optionId = scanner.nextInt();
            scanner.nextLine();
            if(optionId == 1) {
                System.out.println("Enter food items name which you want to remove by comma seperated");
                String names = scanner.nextLine();
                List<Integer> itemIds = getItemIdsByNames(items,names);
                menuItemService.deleteMultipleItems(itemIds);
            } else if(optionId == 2) {
                System.out.println("Enter food items name against which you want to ask questions");
                String names = scanner.nextLine();
                List<Integer> itemIds = getItemIdsByNames(items,names);
                this.submitQuestions(itemIds);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void submitQuestions(List<Integer> itemIds) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<AdminQuestions> questions = new ArrayList<>();
        while (true) {
            System.out.println("Enter question:");
            AdminQuestions question = new AdminQuestions();
            question.question = scanner.nextLine();
            for(int i=0; i< itemIds.size();i++) {
                question.menuItemId = itemIds.get(i);
                questions.add(question);
            }

            System.out.println("Do you want to add more questions?");
            System.out.println("If you want to add more questions, press y to continue and press n to exit.");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("Adding more questions...");
            } else if (choice.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
            scanner.nextLine();
        }
        client.sendRequest("submitquestions",questions);
    }
    
    private List<Integer> getItemIdsByNames(List<DiscardItemInfo> items, String names) {
        List<Integer> itemIds = new ArrayList<>();
        String[] nameArray = names.split(",");
        for (String name : nameArray) {
            name = name.trim();
            for (DiscardItemInfo item : items) {
                if (item.itemName.equalsIgnoreCase(name)) {
                    itemIds.add(item.menuItemId);
                    break;
                }
            }
        }
        return itemIds;
    }
    
    @Override
    public void deleteMultipleItems(List<Integer> ids) {
        client.sendRequest("deleteDiscardItem", ids);
    }
}
