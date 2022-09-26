/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.report.model;

import java.util.List;
import javax.swing.JTable;
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
public class Report {
   private List<String> names;
    private List<Double> values;
}
