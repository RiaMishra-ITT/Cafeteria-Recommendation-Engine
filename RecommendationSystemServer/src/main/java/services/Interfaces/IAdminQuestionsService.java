/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.AdminQuestions;

/**
 *
 * @author ria.mishra
 */
public interface IAdminQuestionsService {
    public void addQuestions(ObjectInputStream input) throws IOException,ClassNotFoundException;
    public List<AdminQuestions> getQuestionsByItemId(int menuItemId)  throws IOException,ClassNotFoundException, SQLException;
}
