/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.store.model;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store {
    private String storeID;
    private String branchName;
    private float dailyTarget;
    private float monthlyTarget;
    private List<String> employees;
    private List<String> sales;
    private List<String> inventory;
}
