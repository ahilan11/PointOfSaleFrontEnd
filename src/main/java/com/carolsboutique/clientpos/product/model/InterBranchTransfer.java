/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.product.model;

import com.carolsboutique.clientpos.store.model.Store;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InterBranchTransfer {
    private String ibtID, productID, toStoreID, fromStoreID;

    private boolean approved, received;
    private String customerPhoneNumber;
    private Date dateRequested, dateApproved;
    private Time timeRequested, timeApproved;
}
