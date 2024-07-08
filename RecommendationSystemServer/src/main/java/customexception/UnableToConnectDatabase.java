/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customexception;

/**
 *
 * @author ria.mishra
 */
public class UnableToConnectDatabase extends Exception{
    public UnableToConnectDatabase(String message) {
        super(message);
    }
}
