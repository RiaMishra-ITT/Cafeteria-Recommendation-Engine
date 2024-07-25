/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customexception;

/**
 *
 * @author ria.mishra
 */
public class FailedToUpdateItemException extends Exception{
    public FailedToUpdateItemException(String message) {
        super(message);
    }
}
