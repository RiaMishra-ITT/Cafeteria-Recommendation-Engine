/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.RoleNotFoundException;
import customexception.UnableToConnectDatabase;
import Repositories.RoleDatabaseRepository;
import java.io.IOException;
import java.sql.SQLException;
import models.Role;
import services.Interfaces.IRoleService;

/**
 *
 * @author ria.mishra
 */
public class RoleService implements IRoleService {
    private final RoleDatabaseRepository releRepository;

    public RoleService() throws UnableToConnectDatabase {
        this.releRepository = new RoleDatabaseRepository();
    }
    
    @Override
    public String getRoleById(int id) throws RoleNotFoundException, IOException{
        try {
            Role role = releRepository.getRoleById(id);
            if(role == null) {
                throw new RoleNotFoundException("Invalid role");
            }

            return role.role;
        } catch (RoleNotFoundException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw new IOException("Failed to update profile");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }
        
    }
    
}
