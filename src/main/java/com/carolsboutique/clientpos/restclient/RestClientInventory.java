/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.sale.model.SaleLineItem;
import com.carolsboutique.clientpos.sale.service.InventoryServiceInt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class RestClientInventory implements InventoryServiceInt{
private SaleLineItem sli;

  private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientInventory() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
    @Override
    public String checkAvailableStock(String productID, String storeID, String quantity) {
         Map map = new HashMap<String,String>();
        map.put("productID", productID);
        map.put("storeID", storeID);
        map.put("quantity", quantity);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/inventory/checkInventory/{storeID}/{productID}/{quantity}";
        webTarget = client.target(url).resolveTemplates(map); 
        String stock = "failure";
        try {
            stock = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    String.class);
        } catch (IOException ex) {
            Logger.getLogger(RestClientInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    @Override
    public String addStock(String productID, String storeID, int quantity) {
                sli = new SaleLineItem();
        sli.setProduct(productID);
        sli.setStoreID(storeID);
        sli.setQuantity(quantity);
          String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/inventory/increaseInventory";
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(sli)));
            System.out.println("good");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientInventory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not good");
        }
        return response.readEntity(String.class);
    }

    @Override
    public String decreaseStock(String productID, String storeID, int quantity) {
                sli = new SaleLineItem();
        sli.setProduct(productID);
        sli.setStoreID(storeID);
        sli.setQuantity(quantity);
        System.out.println(quantity);
          String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/inventory/decreaseInventory";
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(sli)));
            System.out.println("amazing");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientInventory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not good");
        }
        return response.readEntity(String.class);
    }
    
}
