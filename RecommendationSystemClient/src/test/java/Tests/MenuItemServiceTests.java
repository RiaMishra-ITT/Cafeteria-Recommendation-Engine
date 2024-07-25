/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;

import com.mycompany.recommendationsystemclient.Client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import models.MenuItem;
import services.MenuItemService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author ria.mishra
 */
public class MenuItemServiceTests {
    private MenuItemService menuItemService;
    private Client mockClient;
    private Scanner scanner;

    @Before
    public void setUp() {
        mockClient = Mockito.mock(Client.class);
        menuItemService = new MenuItemService(mockClient);
    }

//    @Test
//    public void testAddMenuItem() throws IOException {
//        String input = "Pizza\n500\n1\nAvailable\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        menuItemService.scanner = new Scanner(System.in);
//
//        menuItemService.addMenuItem();
//
//        MenuItem menuItem = new MenuItem(0, "Pizza", 500, "Available", 1);
//        verify(mockClient).sendRequest(eq("addMenuItem"), eq(menuItem));
//        verify(mockClient).receiveResponse();
//    }
//
//    @Test
//    public void testViewAllItems() throws IOException, ClassNotFoundException {
//        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
//                new MenuItem(1, "Poha", 30, "Available", 1),
//                new MenuItem(2, "Boiled Eggs", 50, "Unavailable", 1)
//        ));
//
//        when(mockClient.receiveObjectResponse()).thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(menuItems))));
//
//        menuItemService.viewAllItems();
//
//        verify(mockClient).sendRequest(eq("viewAllItems"), eq(null));
//        verify(mockClient).receiveObjectResponse();
//    }

//    @Test
//    public void testUpdateItem() throws IOException, ClassNotFoundException {
//        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
//                new MenuItem(1, "Poha", 30, "Available", 1),
//                new MenuItem(2, "Boiled Eggs", 50, "Unavailable", 1)
//        ));
//
//        when(mockClient.receiveObjectResponse()).thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(menuItems))));
//
//        menuItemService.updateItem();
//
//        verify(mockClient).sendRequest(eq("viewAllItems"), eq(null));
//        verify(mockClient).receiveObjectResponse();
//    }
//
//    @Test
//    public void testDeleteMenuItem() throws IOException, ClassNotFoundException {
//        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
//                new MenuItem(1, "Poha", 30, "Available", 1),
//                new MenuItem(2, "Boiled Eggs", 50, "Unavailable", 1)
//        ));
//
//        when(mockClient.receiveObjectResponse()).thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(menuItems))));
//
//        menuItemService.deleteMenuItem();
//
//        verify(mockClient).sendRequest(eq("viewAllItems"), eq(null));
//        verify(mockClient).receiveObjectResponse();
//    }

    // Helper method to serialize the list
    private byte[] serialize(List<MenuItem> menuItems) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(menuItems);
        out.flush();
        return bos.toByteArray();
    }
}
