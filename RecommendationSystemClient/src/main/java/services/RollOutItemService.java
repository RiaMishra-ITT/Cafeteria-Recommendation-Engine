/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;

/**
 *
 * @author ria.mishra
 */
public class RollOutItemService {
    Scanner scanner = new Scanner(System.in);
    Client client;
    public RollOutItemService(Client client) {
        this.client = client;
    }
    
    public void rollOutItem() {
        System.out.println("Enter meal type");
        String mealType = scanner.nextLine();
        System.out.println("How many items you want to roll out?");
        int noOfItems = scanner.nextInt();
        client.sendRequest("rollOutItem", mealType,noOfItems);
    }
}
