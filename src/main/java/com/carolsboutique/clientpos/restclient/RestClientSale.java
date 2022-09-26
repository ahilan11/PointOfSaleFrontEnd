/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.sale.model.Promocode;
import com.carolsboutique.clientpos.sale.model.Sale;
import com.carolsboutique.clientpos.sale.model.SaleLineItem;
import com.carolsboutique.clientpos.sale.service.Carrier;
import com.carolsboutique.clientpos.sale.service.iSaleService;
import com.carolsboutique.clientpos.store.model.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nicad
 */
public class RestClientSale implements iSaleService {

    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientSale() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    //Sale
    @Override
    public String addSale(Sale sale) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/sale/addSale";
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(sale)));
            System.out.println("good");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not good");
        }
        return response.readEntity(String.class);
    }

    @Override
    public String addProductToLineItem(SaleLineItem p) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/sale/addProductToLineItem";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(p)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Sale getSale(String storeID) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/sale/getSale/{storeID}";
        webTarget = client.target(url).resolveTemplate("storeID",storeID);
        Sale sale = null;

        try {
            sale = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), 
                    Sale.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesByStore(String storeID) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/sale/getSalesByStore/{storeID}";
        webTarget = client.target(url).resolveTemplate("storeID", storeID);

        List<Sale> storesSales = null;
        try {
            storesSales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storesSales;
    }

    @Override
    public List<Sale> getSalesByEmployee(String employeeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("employeeID", employeeID);

        List<Sale> storesSales = null;
        try {
            storesSales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storesSales;
    }

    @Override
    public List<Sale> getSalesOfProduct(String productID) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getSalesOfProduct";
        webTarget = client.target(url).resolveTemplate("productID", productID);

        List<Sale> storesSales = null;
        try {
            storesSales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storesSales;
    }

    @Override
    public List<Sale> getSalesOfProductByStore(String productID, String storeID) {
        TransferObj transfer = new TransferObj();
        transfer.setObject1(productID);
        transfer.setObject2(storeID);
        
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getSalesOfProductByStore";
        webTarget = client.target(url);
        
        List<Sale> sales = null;
        try {
            sales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesOfProductByEmployee(String productID, String employeeID) {
        TransferObj transfer = new TransferObj();
        transfer.setObject1(productID);
        transfer.setObject2(employeeID);
        
        String url = "";
        webTarget = client.target(url);
        
        List<Sale> sales = null;
        try {
            sales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sales;
    }

    @Override
    public List<Sale> getAllSales() {
        String url = "";
        webTarget = client.target(url);

        List<Sale> sales = null;
        try {
            sales = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Sale[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sales;
    }

    @Override
    public String updateSale(Sale sale) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(sale)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteSale(String saleID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("saleID", saleID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(saleID)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return deleteResponse.readEntity(String.class);
    }

    @Override
    public String clearSaleLineItem(String saleID) {
        String url = "";
        webTarget = client.target(url);
        
        String response = null;
        response = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class );
        return response;
    }

    @Override
    public String removeProductFromLineItem(Product p, Employee manager) {
        TransferObj transfer = new TransferObj();
        transfer.setObject1(p);
        transfer.setObject2(manager);
        
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    //Promo Code
    @Override
    public String addPromocode(Promocode promocde) {
        String url = "";
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(promocde)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Promocode getPromocode(String code) {
        String url = "";
        webTarget = client.target(url);
        Promocode promocode = null;

        try {
            promocode = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), 
                    Promocode.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return promocode;
    }

    @Override
    public List<Promocode> getAllPromode() {
        String url = "";
        webTarget = client.target(url);

        List<Promocode> promocodes = null;
        try {
            promocodes = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Promocode[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return promocodes;
    }

    @Override
    public String updatePromocode(Promocode promocode) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(promocode)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deletePromocode(String code) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("code", code);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(code)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return deleteResponse.readEntity(String.class);
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class TransferObj {
        private Object object1;
        private Object object2;
    }
    
}
