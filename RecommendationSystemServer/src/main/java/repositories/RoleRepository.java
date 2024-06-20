/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import models.Role;
import repositories.Interfaces.IRoleRepository;

/**
 *
 * @author ria.mishra
 */
public class RoleRepository implements IRoleRepository {
    private Database database = new Database();
    @Override
    public Role getRoleById(int roleId) throws SQLException {
        Role role = database.getRoleById(roleId);
        return role;
    }
    
}
