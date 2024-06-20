/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import customexception.RoleNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author ria.mishra
 */
public interface IRoleService {
    public String getRoleById(int id) throws RoleNotFoundException,SQLException;
}
