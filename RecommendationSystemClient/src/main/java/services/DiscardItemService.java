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
                //menuItemService.deleteMultipleItems(itemIds);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
