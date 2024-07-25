/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customexception;

/**
 *
 * @author ria.mishra
 */
public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
