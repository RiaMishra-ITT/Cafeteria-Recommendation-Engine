/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.RoleNotFoundException;
import java.sql.SQLException;
import models.Role;
import repositories.Interfaces.IRoleRepository;
import repositories.RoleRepository;
import services.Interfaces.IRoleService;

/**
 *
 * @author ria.mishra
 */
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository = new RoleRepository();
    
    @Override
    public String getRoleById(int id) throws RoleNotFoundException, SQLException{
        Role role = roleRepository.getRoleById(id);
        if(role == null) {
            throw new RoleNotFoundException("Invalid role");
        }
        
        return role.role;
    }
    
}
