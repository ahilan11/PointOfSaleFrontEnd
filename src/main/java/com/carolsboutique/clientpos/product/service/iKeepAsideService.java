/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.product.service;

import com.carolsboutique.clientpos.product.model.KeepAside;
import com.carolsboutique.clientpos.store.model.Store;
import java.util.List;

/**
 *
 * @author nicad
 */
public interface iKeepAsideService {
    //Create--------------------------------------------------------------------
    String addKeepAside(KeepAside keepAside);
    
    //Read----------------------------------------------------------------------
    KeepAside getKeepAside(KeepAside keepAsideID);
    List<KeepAside> getKeepAsidesByStore(Store storeID);
    List<KeepAside> getAllKeepAsides();
    
    //Update--------------------------------------------------------------------
    String updateKeepAside(KeepAside keepAside);
    
    //Delete--------------------------------------------------------------------
    String deleteKeepAside(KeepAside keepAsideID);
}
