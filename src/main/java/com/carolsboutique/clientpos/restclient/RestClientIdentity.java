/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.email.model.Email;
import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.identity.service.iIdentityGenerator;
import com.carolsboutique.clientpos.product.model.Category;
import com.carolsboutique.clientpos.product.model.InterBranchTransfer;
import com.carolsboutique.clientpos.product.model.KeepAside;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.sale.model.Sale;
import com.carolsboutique.clientpos.store.model.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @author Liam
 */
public class RestClientIdentity implements iIdentityGenerator{
    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientIdentity() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }
    
    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public String generateID(Category category) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(category)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Email email) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(email)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Employee employee) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/identity/generateIDEmployee";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(employee)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(InterBranchTransfer ibt) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(ibt)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(KeepAside keepAside) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(keepAside)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Product product) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(product)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Product product, Category category) {
        TransferObj transfer = new TransferObj();
        transfer.setObject1(product);
        transfer.setObject2(category);
        
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Sale sale) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(sale)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String generateID(Store store) {
        String url = "";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(store)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientIdentity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
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
