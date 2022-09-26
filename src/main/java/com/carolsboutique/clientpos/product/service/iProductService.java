/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.product.service;

import com.carolsboutique.clientpos.product.model.Category;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.store.model.Store;
import java.util.List;

/**
 *
 * @author nicad
 */
public interface iProductService {
    //Create--------------------------------------------------------------------
    String addProduct(Product product);
  
    //Read----------------------------------------------------------------------
    Product getProduct(Product productID);
    List<Product> getProductsByStore(Store storeID);
    List<Product> getProductsByCategory(Category categoryID);
    List<Product> getAllProducts();
    
    //Update--------------------------------------------------------------------
    String updateProduct(Product product);

    //Delete--------------------------------------------------------------------
    String deleteProduct(Product productID);
}
