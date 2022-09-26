/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.product.service;

import com.carolsboutique.clientpos.product.model.InterBranchTransfer;
import com.carolsboutique.clientpos.store.model.Store;
import java.util.List;

/**
 *
 * @author nicad
 */
public interface iIbtService {
     //Create--------------------------------------------------------------------
    String addIBT(InterBranchTransfer transfer);
    
    //Read----------------------------------------------------------------------
    InterBranchTransfer getIbt(InterBranchTransfer transferID);
    List<InterBranchTransfer> getIbtsByStore(Store storeID);
    List<InterBranchTransfer> getAllIbts();
    
    //Update--------------------------------------------------------------------
    String updateIbt(InterBranchTransfer transfer);
    String approveIbt(InterBranchTransfer transfer);
    String receiveIbt(InterBranchTransfer transfer);
   
    //Delete--------------------------------------------------------------------
    String deleteIbt(InterBranchTransfer transferID); 
}
