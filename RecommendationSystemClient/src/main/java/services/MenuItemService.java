/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import models.MenuItem;


public  class MenuItemService {
    Scanner scanner = new Scanner(System.in);
    Client client = new Client();
    public void AddMenuItem() {
        MenuItem menuItem = new MenuItem();
        System.out.println("Add Item name");
        menuItem.itemName = scanner.nextLine();
        System.out.println("Add Item price");
        menuItem.price = scanner.nextInt();
        System.out.println("Add Item type");
        menuItem.itemType = scanner.nextLine();
        System.out.println("Add Item availbility");
        menuItem.availablityStatus = scanner.nextLine();
        Client client = new Client();
        client.sendRequest("addMenuItem", menuItem);
    }
}
