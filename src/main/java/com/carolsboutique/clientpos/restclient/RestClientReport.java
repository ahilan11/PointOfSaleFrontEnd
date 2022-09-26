/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.report.model.Report;
import com.carolsboutique.clientpos.report.service.iReportGenerator;
import com.carolsboutique.clientpos.store.model.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nicad
 */
public class RestClientReport implements iReportGenerator {

    private final Client client;
    private WebTarget webTarget;
    private final ObjectMapper objectMapper;

    public RestClientReport() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<Report> dailyTarget() {
        List<Report> report = null;
        Response response;
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/dailyTarget";
        webTarget = client.target(url);
        try {
            report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public List<Report> monthlyTarget(int month) {
        List<Report> report = null;
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/monthlyTarget/{month}";
        webTarget = client.target(url).resolveTemplate("month", month);
        try {
            report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public List<Report> topSellingStores(int limit) {
        List<Report> report = null;
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topSellingStores/{limit}";
        webTarget = client.target(url).resolveTemplate("limit", limit);
        try {
            report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public List<Report> leastSellingStores(int months, int amount) {
        List<Report> report = null;
        Map map = new HashMap();
        map.put("months", months);
        map.put("amount", amount);
        
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/leastSellingStores/{months}/{amount}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
            report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public List<Report> topRatedStores(int month, int amount) {
        List<Report> report = null;
        Map map = new HashMap();
        map.put("month", month);
        map.put("amount", amount);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topRatedStores/{month}/{amount}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
           report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    @Override
    public List<Report> topStoreEmployees(Store store, int amount) { //
        List<Report> report = null;
        Map map = new HashMap();
        map.put("store", store.getStoreID());
        map.put("amount", amount);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topStoreEmployees/{store}/{amount}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
             report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

  
    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public List<Report> topEmployees(int amount) {
List<Report> report = null;
        Map map = new HashMap();
        map.put("amount", amount);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topStoreEmployees/{store}/{amount}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
             report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;    }

    @Override
    public List<Report> topProducts(Product product) {
List<Report> report = null;
      Map map = new HashMap();
      map.put("product",product.getId());
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topProducts/{product}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
             report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;    }

    @Override
    public List<Report> topSellingProducts() {
List<Report> report = null;

        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topSellingProducts";
        webTarget = client.target(url);
        try {
             report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;    }

    @Override
    public List<Report> topSales(Store store, int month) {
List<Report> report = null;
        Map map = new HashMap();
        map.put("store", store.getStoreID());
        map.put("month", month);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/report/topSales/{store}/{month}";
        webTarget = client.target(url).resolveTemplates(map);
        try {
             report = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Report[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;    }

}
