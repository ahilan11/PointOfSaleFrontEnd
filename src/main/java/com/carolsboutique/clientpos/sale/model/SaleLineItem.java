/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.sale.model;

import com.carolsboutique.clientpos.product.model.Product;
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
public class SaleLineItem {
    private String saleID;
    private String product;
    private Integer quantity;
    private String storeID;
}
