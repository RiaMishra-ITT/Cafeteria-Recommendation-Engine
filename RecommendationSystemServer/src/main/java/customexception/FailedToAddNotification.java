/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customexception;

/**
 *
 * @author ria.mishra
 */
public class FailedToAddNotification extends Exception{
    public FailedToAddNotification(String message) {
        super(message);
    }
}
