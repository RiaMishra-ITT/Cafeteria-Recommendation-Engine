/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.RoleNotFoundException;
import customexception.UnableToConnectDatabase;
import database.RoleDatabaseOperation;
import java.sql.SQLException;
import models.Role;
import services.Interfaces.IRoleService;

/**
 *
 * @author ria.mishra
 */
public class RoleService implements IRoleService {
    private final RoleDatabaseOperation dbOperation;

    public RoleService() throws UnableToConnectDatabase {
        this.dbOperation = new RoleDatabaseOperation();
    }
    
    @Override
    public String getRoleById(int id) throws RoleNotFoundException, SQLException{
        Role role = dbOperation.getRoleById(id);
        if(role == null) {
            throw new RoleNotFoundException("Invalid role");
        }
        
        return role.role;
    }
    
}
