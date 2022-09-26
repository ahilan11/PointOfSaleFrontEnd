/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.email.model.Email;
import com.carolsboutique.clientpos.email.model.SMS;
import com.carolsboutique.clientpos.email.service.iEmailService;
import com.carolsboutique.clientpos.email.service.iSMSService;
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

/**
 *
 * @author nicad
 */
public class RestClientEmail implements iSMSService, iEmailService {

    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientEmail() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    //SMS
    @Override
    public String sendSMS(SMS sms) {
        URLReader reader = new URLReader();
        String url = reader.readRestURL();
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(sms)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    //Email
    @Override
    public String sendEmailRecipt(String toEmail) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/email/sendEmailReceipt";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request().post(Entity.json(stringJson(toEmail)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String sendEmailNotifiction(String toEmail) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/email/sendEmailNotification";
        webTarget = client.target(url);
        
        Response response = null;
        try {
            response = webTarget.request().post(Entity.json(stringJson(toEmail)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public String addEmail(Email email) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/email/addEmail";
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(email)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }
}
