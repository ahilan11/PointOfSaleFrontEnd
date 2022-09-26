/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.employee.model;

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
public class Employee {
    private String employeeID;
    private String name, surname;
    private int role;
    private String password;
    private String storeID;
}
