/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;
import customexception.UnableToConnectDatabase;
import models.AdminQuestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AdminQuestionsService;
import services.Interfaces.IUserNotificationService;
import Repositories.AdminQuestionsRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
/**
 *
 * @author ria.mishra
 */
public class AdminQuestionServiceTests {
    private AdminQuestionsService adminQuestionsService;
    private AdminQuestionsRepository adminQuestionsRepository;
    private IUserNotificationService notificationService;

    @BeforeEach
    void setUp() throws UnableToConnectDatabase {
        adminQuestionsRepository = mock(AdminQuestionsRepository.class);
        notificationService = mock(IUserNotificationService.class);
        adminQuestionsService = new AdminQuestionsService();
    }

    @Test
    void testAddQuestions() throws Exception {
        List<AdminQuestions> questions = new ArrayList<>();
        questions.add(new AdminQuestions(1, "Question 1", 1));
        questions.add(new AdminQuestions(2, "Question 2", 2));

        byte[] serializedQuestions = serialize(questions);
        ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(serializedQuestions));

        doNothing().when(adminQuestionsRepository).addQuestions(anyList());
        doNothing().when(notificationService).sendDetailFeedbackNotification(anySet());

        adminQuestionsService.addQuestions(input);

        verify(adminQuestionsRepository, times(1)).addQuestions(questions);
        Set<Integer> distinctFoodItemIds = questions.stream().map(q -> q.menuItemId).collect(Collectors.toSet());
        verify(notificationService, times(1)).sendDetailFeedbackNotification(distinctFoodItemIds);
    }

    @Test
    void testAddQuestionsThrowsIOException() throws Exception {
        List<AdminQuestions> questions = new ArrayList<>();
        questions.add(new AdminQuestions(1, "Question 1", 1));

        byte[] serializedQuestions = serialize(questions);
        ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(serializedQuestions));

        doThrow(SQLException.class).when(adminQuestionsRepository).addQuestions(anyList());

        assertThrows(IOException.class, () -> adminQuestionsService.addQuestions(input));
    }

    @Test
    void testGetQuestionsByItemId() throws Exception {
        List<AdminQuestions> questions = new ArrayList<>();
        questions.add(new AdminQuestions(1, "Question 1", 1));

        when(adminQuestionsRepository.getQuestionsByMenuItem(1)).thenReturn(questions);

        List<AdminQuestions> result = adminQuestionsService.getQuestionsByItemId(1);

        assertEquals(questions, result);
        verify(adminQuestionsRepository, times(1)).getQuestionsByMenuItem(1);
    }

    @Test
    void testGetQuestionsByItemIdThrowsIOException() throws Exception {
        when(adminQuestionsRepository.getQuestionsByMenuItem(1)).thenThrow(SQLException.class);

        assertThrows(IOException.class, () -> adminQuestionsService.getQuestionsByItemId(1));
    }

    private byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        return baos.toByteArray();
    }
}
