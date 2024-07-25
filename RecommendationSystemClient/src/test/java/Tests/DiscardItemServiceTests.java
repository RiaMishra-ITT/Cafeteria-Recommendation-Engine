/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;


import com.mycompany.recommendationsystemclient.Client;
import models.DiscardItemInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiscardItemService;
import services.Interfaces.IMenuItemService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

/**
 *
 * @author ria.mishra
 */
public class DiscardItemServiceTests {
    private Client client;
    private DiscardItemService discardItemService;
    private IMenuItemService menuItemService;

    @BeforeEach
    void setUp() {
        client = Mockito.mock(Client.class);
        menuItemService = Mockito.mock(IMenuItemService.class);
        discardItemService = new DiscardItemService(client);
    }

    @Test
    void testViewAllDiscardItemsNoItems() throws Exception {
        Mockito.when(client.receiveObjectResponse())
                .thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(new ArrayList<>()))));
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        discardItemService.viewAllDiscardItems();

        Mockito.verify(client, Mockito.times(1)).sendRequest("viewDiscardItems", null);
        Mockito.verify(client, Mockito.times(1)).receiveObjectResponse();
        Mockito.verifyNoMoreInteractions(client);
        Mockito.verifyNoInteractions(menuItemService);
    }

    @Test
    void testViewAllDiscardItemsRemoveItems() throws Exception {
        List<DiscardItemInfo> items = new ArrayList<>();
        items.add(new DiscardItemInfo(1, "Pizza", "4.5", "Positive"));
        Mockito.when(client.receiveObjectResponse())
                .thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(items))));

        System.setIn(new ByteArrayInputStream("1\nPizza\n".getBytes()));
        discardItemService.viewAllDiscardItems();

        Mockito.verify(client, Mockito.times(1)).sendRequest("viewDiscardItems", null);
        Mockito.verify(client, Mockito.times(1)).receiveObjectResponse();
        Mockito.verify(menuItemService, Mockito.times(1)).deleteMultipleItems(Mockito.anyList());
    }

    @Test
    void testViewAllDiscardItemsSubmitQuestions() throws Exception {
        List<DiscardItemInfo> items = new ArrayList<>();
        items.add(new DiscardItemInfo(1, "Pizza", "4.5", "Positive"));
        Mockito.when(client.receiveObjectResponse())
                .thenReturn(new ObjectInputStream(new ByteArrayInputStream(serialize(items))));

        System.setIn(new ByteArrayInputStream("2\nPizza\nWhy didn't you like the Pizza?\nn\n".getBytes()));

        discardItemService.viewAllDiscardItems();
        
        Mockito.verify(client, Mockito.times(1)).sendRequest("viewDiscardItems", null);
        Mockito.verify(client, Mockito.times(1)).receiveObjectResponse();
        Mockito.verify(client, Mockito.times(1)).sendRequest("submitquestions", Mockito.anyList());
    }

    @Test
    void testDeleteMultipleItems() throws Exception {
        List<Integer> ids = List.of(1, 2, 3);
        discardItemService.deleteMultipleItems(ids);

        Mockito.verify(client, Mockito.times(1)).sendRequest("deleteDiscardItem", ids);
        Mockito.verify(client, Mockito.times(1)).receiveStringResponse();
    }

    private byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        return baos.toByteArray();
    }
}

