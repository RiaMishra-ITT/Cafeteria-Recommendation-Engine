/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;

import com.mycompany.recommendationsystemclient.Client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Feedback;
import models.RolledOutItem;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import services.EmployeeService;
import services.Interfaces.IMenuItemService;

/**
 *
 * @author ria.mishra
 */
public class EmployeeServiceTests {
    private Client client;
    private EmployeeService employeeService;
    private IMenuItemService menuItemService;

    @BeforeEach
    void setUp() {
        client = Mockito.mock(Client.class);
        menuItemService = Mockito.mock(IMenuItemService.class);
        employeeService = new EmployeeService(client);
    }

    @Test
    void testSubmitFeedback() throws Exception {
        Feedback feedback = new Feedback(1, "Good food", "4.5", LocalDate.now().toString(), 1, 2);
        Mockito.when(client.receiveStringResponse()).thenReturn("Feedback submitted");
        System.setIn(new ByteArrayInputStream("1\n4.5\nGood food\n".getBytes()));
        employeeService.submitFeedback();
        Mockito.verify(client, Mockito.times(1)).sendRequest("submitFeedback", feedback);
        Mockito.verify(client, Mockito.times(1)).receiveStringResponse();
    }

    @Test
    void testViewRolledOutItems() throws Exception {
        List<RolledOutItem> items = new ArrayList<>();
        items.add(new RolledOutItem(1, "Pizza", 10.0, "Available", 2, "4.5", "Positive", "Main Course", "Spicy", "Italian"));
        Mockito.when(client.receiveObjectResponse())
                .thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(items))));
        employeeService.viewRolledOutItems();

        Mockito.verify(client, Mockito.times(1)).sendRequest("viewRolledOutItem", 2);
        Mockito.verify(client, Mockito.times(1)).receiveObjectResponse();
    }

    private byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        return baos.toByteArray();
    }
}
