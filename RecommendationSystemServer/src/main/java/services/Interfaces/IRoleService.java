/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import customexception.RoleNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author ria.mishra
 */
public interface IRoleService {
    String getRoleById(int id) throws RoleNotFoundException, IOException;
}
