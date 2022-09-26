/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.store.service;

import com.carolsboutique.clientpos.store.model.Review;
import com.carolsboutique.clientpos.store.model.Store;
import java.util.List;

/**
 *
 * @author nicad
 */
public interface iStoreService {
    //Create--------------------------------------------------------------------
    public String addStore(Store store);
    
    //Read----------------------------------------------------------------------
    public Store getStore(String storeID);
    public List<Store> getStoresByProduct(String productID);
    
    //Update--------------------------------------------------------------------
    public String updateStore(Store store);
    
    //Delete--------------------------------------------------------------------
    public  String deleteStore(String storeID);
    
    //FOR REVIEW
    //Create--------------------------------------------------------------------
    public String addReview(Review review);
    
    //Read----------------------------------------------------------------------
    public Review getReview(String reviewID);
    public List<Review> getReviewsByStore(String storeID);
    public List<Review> getAllReviews();
  
    //Delete--------------------------------------------------------------------
    public  String deleteReview(String reviewID);
}
