/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import java.util.Scanner;
import services.MenuItemService;


public class AdminOperation {
    
    private Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Update Menu Item");
            System.out.println("3. Read Menu");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Exit");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        if(input == 1) {
            MenuItemService menuItemService = new MenuItemService();
            menuItemService.AddMenuItem();
        }
    }
}
