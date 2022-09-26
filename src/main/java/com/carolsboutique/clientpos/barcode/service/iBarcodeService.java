/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.barcode.service;

import com.carolsboutique.clientpos.product.model.Product;

/**
 *
 * @author nicad
 */
public interface iBarcodeService {
    void generateProductBarcode(Product product);
}
