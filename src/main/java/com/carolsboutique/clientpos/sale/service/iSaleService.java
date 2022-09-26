/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.sale.service;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.sale.model.Promocode;
import com.carolsboutique.clientpos.sale.model.Sale;
import com.carolsboutique.clientpos.sale.model.SaleLineItem;
import com.carolsboutique.clientpos.store.model.Store;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */
public interface iSaleService {
    //Create--------------------------------------------------------------------
    public String addSale(Sale sale);
    public String addProductToLineItem(SaleLineItem p);
    
    //Read----------------------------------------------------------------------
    public Sale getSale(String saleID);
    public List<Sale> getSalesByStore(String storeID);
    public List<Sale> getSalesByEmployee(String employeeID);
    public List<Sale> getSalesOfProduct(String productID);
    public List<Sale> getSalesOfProductByStore(String productID, String storeID);
    public List<Sale> getSalesOfProductByEmployee(String productID, String employeeID);
    public List<Sale> getAllSales();
    
    //Update--------------------------------------------------------------------
    public String updateSale(Sale sale);
    
    //Delete--------------------------------------------------------------------
    public String deleteSale(String saleID);
    public String clearSaleLineItem(String saleID);
    public String removeProductFromLineItem(Product p, Employee manager);

    //PromoCode
    //Create--------------------------------------------------------------------
    public String addPromocode(Promocode promocde);
    
    //Read----------------------------------------------------------------------
    public Promocode getPromocode(String code);
    public List<Promocode> getAllPromode();
    
    //Update--------------------------------------------------------------------
    public String updatePromocode(Promocode promocode);
    
    //Delete--------------------------------------------------------------------
    public String deletePromocode(String code);
  
}
