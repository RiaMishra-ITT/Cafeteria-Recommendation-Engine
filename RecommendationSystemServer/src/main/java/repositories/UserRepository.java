/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import java.util.List;
import models.User;
import repositories.Interfaces.IUserRepository;

/**
 *
 * @author ria.mishra
 */
public class UserRepository implements IUserRepository{
    private Database database = new Database();
    @Override
    public List<User> getUserByRoleId(int roleId) throws SQLException {
        return database.getUserByRole(roleId);
    }
    
}
