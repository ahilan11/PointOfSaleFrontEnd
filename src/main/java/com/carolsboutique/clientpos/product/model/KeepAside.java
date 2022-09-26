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
import lombok.ToString;

/**
 *
 * @author nicad
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KeepAside {
    private String keepAsideID, productID, storeID;
    private String customerEmail;
  
    private Date dateCreated;
    private Time timeCreated;   
}
