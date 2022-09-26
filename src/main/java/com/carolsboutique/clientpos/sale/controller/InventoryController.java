/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.carolsboutique.clientpos.sale.controller;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.product.service.iProductService;
import com.carolsboutique.clientpos.restclient.RestClientInventory;
import com.carolsboutique.clientpos.restclient.RestClientProduct;
import com.carolsboutique.clientpos.restclient.RestClientSale;
import com.carolsboutique.clientpos.sale.model.Promocode;
import com.carolsboutique.clientpos.sale.model.Sale;
import com.carolsboutique.clientpos.sale.model.SaleLineItem;
import com.carolsboutique.clientpos.sale.service.Carrier;
import com.carolsboutique.clientpos.sale.service.InventoryServiceInt;
import com.carolsboutique.clientpos.sale.service.iSaleService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vijay
 */
@WebServlet(name = "InventoryController", urlPatterns = {"/InventoryController"})

public class InventoryController extends HttpServlet {


private InventoryServiceInt invService;
    public InventoryController() {
       
        invService = new RestClientInventory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getParameter("submit")) {
 
            case "ADD STOCK":
                invService.addStock(request.getParameter("productID"), (String)request.getSession(false).getAttribute("storeID"), Integer.parseInt(request.getParameter("amountID")));
          RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
          rd.forward(request, response);
                break;
        }

    }

}
