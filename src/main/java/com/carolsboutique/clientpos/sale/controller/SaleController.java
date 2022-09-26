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
@WebServlet(name = "SaleController", urlPatterns = {"/SaleController"})

public class SaleController extends HttpServlet {

    private iSaleService saleService;
    private iProductService prodService;
private InventoryServiceInt invService;
    public SaleController() {
        saleService = new RestClientSale();
        prodService = new RestClientProduct();
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

            case "ADD TO CART":
                if (request.getSession(false).getAttribute("salelineitem") == null) {
                    Map salelineitem = new HashMap();
                    request.getSession(false).setAttribute("salelineitem", salelineitem);
                }
                Product p = new Product();
                String prdID = request.getParameter("productID");
                p.setId((prdID));
                Product prod = prodService.getProduct(p);
                Map map = (Map) request.getSession(false).getAttribute("salelineitem");
                String quantity = (request.getParameter("amountID"));
                System.out.println(quantity);
                String success = invService.checkAvailableStock(prdID, (String)request.getSession(false).getAttribute("storeID"), quantity);
                System.out.println(success);
                if(success.equals("success")){
                    
                map.put(prod, quantity+ "");
                RequestDispatcher rd = request.getRequestDispatcher("NewSale.jsp");
                rd.forward(request, response);
                }
                else {
                    RequestDispatcher rd = request.getRequestDispatcher("NewSale.jsp");
                    request.setAttribute("failure", "Could not add the amount of stock selected to your cart");
                rd.forward(request, response);
                }
                break;
            case "MAKE SALE":
                String saleID = "SLNO";
                for (int i = 0; i < 8; i++) {
                    saleID += (char) ((int) (Math.random() * (88 - 65) + 65)) + (int) (Math.random() * (9 - 0));
                }
                saleService.addSale(new Sale(saleID, (LocalDate.now()).toString(), (LocalTime.now()).toString(), (String) request.getSession(false).getAttribute("storeID"), (String) request.getSession(false).getAttribute("employeeID"), null, null));
                Map salelineitems = (Map) request.getSession(false).getAttribute("salelineitem");
                Iterator<Map.Entry<Product, String>> itr = salelineitems.entrySet().iterator();

                while (itr.hasNext()) {
                    Map.Entry<Product, String> entry = itr.next();
                    SaleLineItem c = new SaleLineItem();
                    c.setProduct(entry.getKey().getId());
                    c.setQuantity(Integer.parseInt(entry.getValue()));
                    c.setSaleID(saleID);
invService.decreaseStock(entry.getKey().getId(), (String)request.getSession(false).getAttribute("storeID"), Integer.parseInt(entry.getValue()));
                    saleService.addProductToLineItem(c);
                }
                RequestDispatcher rda = request.getRequestDispatcher("MainMenu.jsp");
                request.getSession(false).setAttribute("salelineitem", null);
                rda.forward(request, response);
                break;
            case "REMOVE ITEM/S":
                if (request.getSession(false).getAttribute("salelineitem") != null) {

                    Map removeMap = (Map) request.getSession(false).getAttribute("salelineitem");
                    Iterator<Map.Entry<Product, String>> it = removeMap.entrySet().iterator();
          Product product = null;
        while(it.hasNext())
        {
             Map.Entry<Product, String> entry = it.next();
             if(entry.getKey().getId().equals(request.getParameter("productID"))){
              product = entry.getKey();
             }
        }
                    
                    removeMap.remove(product);
                    System.out.println(removeMap.toString());
                    request.getSession(false).setAttribute("salelineitem", removeMap);
                }
                RequestDispatcher rdi = request.getRequestDispatcher("NewSale.jsp");
                rdi.forward(request, response);
                break;

            case "Add to Sale Line Item":
//                saleService.addProductToLineItem(new Product(request.getParameter("ID"), request.getParameter("name"), request.getParameter("description"), request.getParameter("size"), Double.parseDouble(request.getParameter("price")), Boolean.parseBoolean(request.getParameter("onsale")), Double.parseDouble(request.getParameter("onSalePrice"))));
                break;

            case "Clear Sale Line Item":
                saleService.clearSaleLineItem(request.getParameter("ID"));
                break;

            case "Remove Product From Line Item":
                saleService.removeProductFromLineItem(new Product(request.getParameter("ID"), request.getParameter("name"), request.getParameter("description"), request.getParameter("size"), Double.parseDouble(request.getParameter("price")), Boolean.parseBoolean(request.getParameter("onsale")), Double.parseDouble(request.getParameter("onSalePrice"))), new Employee(request.getParameter("employeeID"), request.getParameter("EmployeeName"), request.getParameter("surname"), Integer.parseInt(request.getParameter("role")), request.getParameter("password"), request.getParameter("storeID")));
                break;
            case "Get Sale":
                saleService.getSale(request.getParameter("saleID"));
                break;

            case "Add Promocode":
                saleService.addPromocode(new Promocode(request.getParameter("name"), Double.parseDouble(request.getParameter("discount"))));
                break;

            case "Delete Promocode":
                saleService.deletePromocode(request.getParameter("code"));
                break;

            case "Delete Sale":
                saleService.deleteSale(request.getParameter("saleID"));
                break;

            case "Get Sales By Employee":
                saleService.getSalesByEmployee(request.getParameter("employeeID"));
                break;

            case "Get sales by store":
                saleService.getSalesByStore(request.getParameter("storeID"));
                break;
            case "Get Sales of Product":
                saleService.getSalesOfProduct(request.getParameter("productID"));
                break;

            case "Get Sales of Product By Employee":
                saleService.getSalesOfProductByEmployee(request.getParameter("productID"), request.getParameter("employeeID"));
                break;

            case "Get Sales of Product by Store":
                saleService.getSalesOfProductByStore(request.getParameter("productID"), request.getParameter("storeID"));
                break;
            case "Get All Promocodes":
                saleService.getAllPromode();
                break;

            case "Get All Sales":
                saleService.getAllSales();
                break;

            case "Update Sale ":

                saleService.updateSale(new Sale(request.getParameter("ID"),
                        (LocalDate.now()).toString(), (LocalTime.now()).toString(),
                        request.getParameter("storeID"), request.getParameter("employeeID"),
                        null, new Promocode(request.getParameter("promocode"),
                                Double.parseDouble("discount"))));
                break;

            case "Update Promocode":
                saleService.updatePromocode(new Promocode(request.getParameter("name"), Double.parseDouble(request.getParameter("discount"))));
                break;

            case "Get Promocode":

                saleService.getPromocode(request.getParameter("promocode"));
                break;

            //RequestDispatcher
            case "SaleReport":
                request.getRequestDispatcher("Report.jsp").forward(request, response);
                break;
        }

    }

}
