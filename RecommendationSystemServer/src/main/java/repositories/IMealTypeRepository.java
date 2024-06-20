/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import models.MealType;


public interface IMealTypeRepository {
    void addMealType(MealType mealType);
    void deleteMealType(int mealTypeId);
    void updateMealType(MealType mealType);
    void getAllMealType();
}
