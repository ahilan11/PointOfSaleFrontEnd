/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.product.service;

import com.carolsboutique.clientpos.product.model.Category;
import java.util.List;

/**
 *
 * @author nicad
 */
public interface iCategoryService {
     //Create--------------------------------------------------------------------
    String addCategory(Category category);
    
    //Read----------------------------------------------------------------------
    Category getCategory(Category categoryID);
    List<Category> getAllCategories();
    
    //Update--------------------------------------------------------------------
    String updateCategory(Category category);
    
    //Delete--------------------------------------------------------------------
    String deleteCategory(Category categoryID);
}
