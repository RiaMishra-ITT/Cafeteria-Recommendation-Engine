/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author ria.mishra
 */
public interface IUserResponseService {
    public void addResponse(ObjectInputStream input) throws IOException,ClassNotFoundException;
}
