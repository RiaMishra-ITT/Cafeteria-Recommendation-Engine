/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import models.Role;

/**
 *
 * @author ria.mishra
 */
public interface IRoleRepository {
    public Role getRoleById(int roleId) throws SQLException;
}