/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import models.Profile;
import services.Interfaces.IProfileService;

/**
 *
 * @author ria.mishra
 */
public class ProfileService implements IProfileService{
    Scanner scanner = new Scanner(System.in);
    Client client;
    public ProfileService(Client client) {
        this.client = client;
    }
    
    @Override
    public void updateProfile() {
        try {
            System.out.println("Please answer these questions to know your prefrences");
            System.out.println("Please select one");
            System.out.println("- Vegetarian");
            System.out.println("- NonVegetarian");
            System.out.println("- Eggetarian");
            String dietary = scanner.nextLine();

            System.out.println("Please select your spice level");
            System.out.println("- High");
            System.out.println("- Medium");
            System.out.println("- Low");
            String spiceLevel = scanner.nextLine();

            System.out.println("what do you prefer most?");
            System.out.println("North indian");
            System.out.println("South indian");
            System.out.println("Other");
            String cusine = scanner.nextLine();

            System.out.println("Do you have sweet tooth?");
            System.out.println("- Yes");
            System.out.println("- No");
            String sweetTooth = scanner.nextLine();

            Profile profile = new Profile(Authentication.userId,dietary,spiceLevel,cusine,sweetTooth);
            client.sendRequest("updateProfile", profile);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Profile updated");
        } catch (Exception ex) {
            System.out.println("Client response - Failed to update profile");
        }
    }
    
}
