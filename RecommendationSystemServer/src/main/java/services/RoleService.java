/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.RoleNotFoundException;
import customexception.UnableToConnectDatabase;
import Repositories.RoleDatabaseRepository;
import java.sql.SQLException;
import models.Role;
import services.Interfaces.IRoleService;

/**
 *
 * @author ria.mishra
 */
public class RoleService implements IRoleService {
    private final RoleDatabaseRepository dbOperation;

    public RoleService() throws UnableToConnectDatabase {
        this.dbOperation = new RoleDatabaseRepository();
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
