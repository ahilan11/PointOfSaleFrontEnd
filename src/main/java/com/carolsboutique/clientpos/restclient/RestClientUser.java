/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.restclient;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.employee.service.iEmployeeService;
import com.carolsboutique.clientpos.product.model.Product;
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
public class RestClientUser implements iEmployeeService {

    private Client client;
    private WebTarget webTarget;
    private ObjectMapper objectMapper;

    public RestClientUser() {
        client = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    private String stringJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    @Override
    public String addEmployee(Employee employee) {
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/employee/addEmployee";
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(stringJson(employee)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    @Override
    public Employee getEmployee(String employeeID) {
        Map map = new HashMap();
        map.put("employeeID", employeeID);
        String url = "http://localhost:8080/com.za_CarolsStore_war_1.0-SNAPSHOT/pos/employee/getEmployee/{employeeID}";
        webTarget = client.target(url).resolveTemplates(map);
        Employee employee = null;

        try {
            employee = objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),
                    Employee.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String url = "";
        webTarget = client.target(url);

        List<Employee> employees = null;
        try {
            employees = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Employee[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByStore(String storeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("storeID", storeID);

        List<Employee> storesEmployees = null;
        try {
            storesEmployees = Arrays.asList(objectMapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Employee[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return storesEmployees;
    }

    @Override
    public String updateEmployee(Employee employee) {
        String url = "";
        webTarget = client.target(url);

        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(employee)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String updateEmployeeRole(Employee employee) {
        String url = "";
        webTarget = client.target(url);
        
        Response updateResponse = null;
        try {
            updateResponse = webTarget.request().post(Entity.json(stringJson(employee)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResponse.readEntity(String.class);
    }

    @Override
    public String deleteEmployee(String employeeID) {
        String url = "";
        webTarget = client.target(url).resolveTemplate("employeeID", employeeID);

        Response deleteResponse = null;
        try {
            deleteResponse = webTarget.request().post(Entity.json(stringJson(employeeID)));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return deleteResponse.readEntity(String.class);
    }
}
