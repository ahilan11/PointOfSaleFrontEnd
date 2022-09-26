/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.store.model.Review;
import com.carolsboutique.clientpos.store.model.Store;
import com.carolsboutique.clientpos.store.service.iStoreService;
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

/**
 *
 * @author nicad
 */
public class RestClientStore implements iStoreService {

    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientStore() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    //Store
    @Override
    public String addStore(Store store) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/store/addStore";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(store)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Store getStore(String storeID) {
        String url = "";
        webTarget = client.target(url);
        Store store = null;

        try {
            store = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), 
                    Store.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return store;
    }

    @Override
    public String updateStore(Store store) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(store)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteStore(String storeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("storeID", storeID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(storeID)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return deleteResponse.readEntity(String.class);
    }

    //Review
    @Override
    public String addReview(Review review) {
        String url = "";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(review)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Review getReview(String reviewID) {
        String url = "";
        webTarget = client.target(url);
        Review review = null;

        try {
            review = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), 
                    Review.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return review;
    }

    @Override
    public List<Review> getReviewsByStore(String reviewID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("reviewID", reviewID);

        List<Review> storesReviews = null;
        try {
            storesReviews = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Review[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storesReviews;
    }

    @Override
    public List<Review> getAllReviews() {
        String url = "";
        webTarget = client.target(url);

        List<Review> reviews = null;
        try {
            reviews = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Review[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

    @Override
    public String deleteReview(String reviewID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("reviewID", reviewID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(reviewID)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return deleteResponse.readEntity(String.class);
    }

    @Override
    public List<Store> getStoresByProduct(String productID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("productID", productID);
        
        List<Store> stores = null;
        try {
            stores = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Store[].class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stores;
    }

}
