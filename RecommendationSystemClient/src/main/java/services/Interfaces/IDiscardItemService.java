/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.util.List;

/**
 *
 * @author ria.mishra
 */
public interface IDiscardItemService {
    public void viewAllDiscardItems();
    public void deleteMultipleItems(List<Integer> ids);
}
