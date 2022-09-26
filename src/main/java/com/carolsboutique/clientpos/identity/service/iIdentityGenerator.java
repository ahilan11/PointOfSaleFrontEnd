/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.identity.service;

import com.carolsboutique.clientpos.email.model.Email;
import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Category;
import com.carolsboutique.clientpos.product.model.InterBranchTransfer;
import com.carolsboutique.clientpos.product.model.KeepAside;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.sale.model.Sale;
import com.carolsboutique.clientpos.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */
public interface iIdentityGenerator {
    public String generateID(Category category);
    public String generateID(Email email);
    public String generateID(Employee employee);
    public String generateID(InterBranchTransfer ibt);
    public String generateID(KeepAside keepAside);
    public String generateID(Product product);
    public String generateID(Product product, Category category);
    public String generateID(Sale sale);
    public String generateID(Store store);
  //  public String generateID(Transaction transaction);
  //  public String generateID(Review review);
}
