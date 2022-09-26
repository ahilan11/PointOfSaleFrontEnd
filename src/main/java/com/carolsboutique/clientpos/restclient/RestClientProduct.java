/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.product.model.Category;
import com.carolsboutique.clientpos.product.model.InterBranchTransfer;
import com.carolsboutique.clientpos.product.model.KeepAside;
import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.product.service.iCategoryService;
import com.carolsboutique.clientpos.product.service.iIbtService;
import com.carolsboutique.clientpos.product.service.iKeepAsideService;
import com.carolsboutique.clientpos.product.service.iProductService;
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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nicad
 */
public class RestClientProduct implements iProductService, iKeepAsideService, iIbtService, iCategoryService {

    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientProduct() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    //Product
    @Override
    public String addProduct(Product product) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/addProduct";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(product)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);

    }

    @Override
    public Product getProduct(Product p) {
        Map map = new HashMap();
        map.put("productID", p.getId());
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getProduct/{productID}";
        webTarget = client.target(url).resolveTemplates(map); 
        Product product = null;
        try {
            product = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Product.class);
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
 public List<Store> getStoresByProduct(String productID){
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getProductsByStore/{productID}";
        webTarget = client.target(url).resolveTemplate("productID", productID);

        List<Store> storesProducts = null;
        try {
            storesProducts = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Store[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storesProducts;
   }
    @Override
    public List<Product> getProductsByStore(Store store) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getProductsByStore";
        webTarget = client.target(url).resolveTemplate("storeID", store);

        List<Product> storesProducts = null;
        try {
            storesProducts = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Product[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storesProducts;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getProductsByCategory";
        webTarget = client.target(url).resolveTemplate("category", category);

        List<Product> categoryProducts = null;
        try {
            categoryProducts = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Product[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryProducts;
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/getAllProducts";
        webTarget = client.target(url);

        List<Product> products = null;
        try {
            products = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Product[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public String updateProduct(Product product) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/updateProduct";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(product)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteProduct(Product product) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/deleteProduct";
        webTarget = client.target(url).resolveTemplate("product", product);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(product)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResponse.readEntity(String.class);
    }

    //KeepAside
    @Override
    public String addKeepAside(KeepAside keepAside) {
        String url = "";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(keepAside)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public KeepAside getKeepAside(KeepAside keepAsideID) {
        String url = "";
        webTarget = client.target(url);
        KeepAside keepAside = null;

        try {
            keepAside = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), KeepAside.class);
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keepAside;
    }

    @Override
    public List<KeepAside> getKeepAsidesByStore(Store storeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("storeID", storeID);

        List<KeepAside> storesKeepAside = null;
        try {
            storesKeepAside = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), KeepAside[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storesKeepAside;
    }

    @Override
    public List<KeepAside> getAllKeepAsides() {
        String url = "";
        webTarget = client.target(url);

        List<KeepAside> keepAsides = null;
        try {
            keepAsides = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), KeepAside[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keepAsides;
    }

    @Override
    public String updateKeepAside(KeepAside keepAside) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(keepAside)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteKeepAside(KeepAside keepAsideID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("keepAsideID", keepAsideID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(keepAsideID)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResponse.readEntity(String.class);
    }

    //IBT
    @Override
    public String addIBT(InterBranchTransfer transfer) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/product/addIbt";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public InterBranchTransfer getIbt(InterBranchTransfer transferID) {
        String url = "";
        webTarget = client.target(url);
        InterBranchTransfer interBranchTransfer = null;

        try {
            interBranchTransfer = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class)
                    , InterBranchTransfer.class);
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return interBranchTransfer;
    }

    @Override
    public List<InterBranchTransfer> getIbtsByStore(Store storeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("storeID", storeID);

        List<InterBranchTransfer> storesInterBranchTransfers = null;
        try {
            storesInterBranchTransfers = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), InterBranchTransfer[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storesInterBranchTransfers;
    }

    @Override
    public List<InterBranchTransfer> getAllIbts() {
        String url = "";
        webTarget = client.target(url);

        List<InterBranchTransfer> interBranchTransfers = null;
        try {
            interBranchTransfers = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), InterBranchTransfer[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return interBranchTransfers;
    }

    @Override
    public String updateIbt(InterBranchTransfer transfer) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String approveIbt(InterBranchTransfer transfer) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("transfer", transfer);

        Response approveResponse = null;
        try {
            approveResponse = webTarget.request().post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return approveResponse.readEntity(String.class);
    }

    @Override
    public String receiveIbt(InterBranchTransfer transfer) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("transfer", transfer);

        Response approveResponse = null;
        try {
            approveResponse = webTarget.request().post(Entity.json(stringJson(transfer)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return approveResponse.readEntity(String.class);
    }

    @Override
    public String deleteIbt(InterBranchTransfer transferID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("transferID", transferID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(transferID)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResponse.readEntity(String.class);
    }

    //Category
    @Override
    public String addCategory(Category category) {
        String url = "";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(category)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Category getCategory(Category categoryID) {
        String url = "";
        webTarget = client.target(url);
        Category category = null;

        try {
            category = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), 
                    Category.class);
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        String url = "";
        webTarget = client.target(url);

        List<Category> categorys = null;
        try {
            categorys = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Category[].class));
        } catch (IOException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
    }

    @Override
    public String updateCategory(Category category) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(category)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteCategory(Category categoryID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("categoryID", categoryID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(categoryID)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResponse.readEntity(String.class);
    }
}
