/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.sale.service;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Vijay
 */
@NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Carrier{
        Product product;
        Store store;
        Integer number;
        Employee employee;
        String sale;
    }