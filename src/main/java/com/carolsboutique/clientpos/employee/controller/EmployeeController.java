/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.carolsboutique.clientpos.employee.controller;

import com.carolsboutique.clientpos.employee.model.Employee;
import com.carolsboutique.clientpos.employee.service.iEmployeeService;
import com.carolsboutique.clientpos.identity.service.iIdentityGenerator;
import com.carolsboutique.clientpos.restclient.RestClientUser;
import com.carolsboutique.clientpos.store.model.Store;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Vijay
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})

public class EmployeeController extends HttpServlet {

    private iEmployeeService employeeService;
    private iIdentityGenerator idGen;

    public EmployeeController(){
        employeeService = new RestClientUser();
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("submit")){
           
                 case "LOG IN":
                     String empID = request.getParameter("employeeID");
                Employee emp = employeeService.getEmployee(empID);
                if(emp.getPassword().equals(request.getParameter("employeePassword"))){
                
                RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("employeeID", empID);
                session.setAttribute("storeID",emp.getStoreID());
                rd.forward(request, response);
                }
                else{
                RequestDispatcher rd = request.getRequestDispatcher("error.html");
                                rd.forward(request, response);

                }
                break;
                
            case "REGISTER":
                String employeeID = "EMPL";
                if(request.getParameter("employeeConfirmPassword").equals(request.getParameter("employeePassword"))){
                for(int i = 0; i<3;i++){
                    employeeID += (char)((int)(Math.random()*(88-65)+65)) + (int)(Math.random()*(9-0));
                }
                System.out.println(employeeID);
               String pass =  employeeService.addEmployee(new Employee(employeeID, request.getParameter("employeeName"),request.getParameter("employeeSurname"),Integer.parseInt(request.getParameter("employeeRole")),request.getParameter("employeePassword"),(String)request.getSession(false).getAttribute("storeID")));
                if(!pass.equalsIgnoreCase("failure")){
                    RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
                    rd.forward(request, response);
                }
                else{
                     RequestDispatcher rd = request.getRequestDispatcher("RegisterEmployee.jsp");
                    rd.forward(request, response);
                }
                }
                 else{
                     RequestDispatcher rd = request.getRequestDispatcher("RegisterEmployee.jsp");
                    rd.forward(request, response);
                }
               break;
              
            case "Delete Employee":
                employeeService.deleteEmployee(request.getParameter("employeeID"));
                break;
                
            case "Get All Employees":
                employeeService.getAllEmployees();
                break;
                
            case "Get Employees By Store":
                employeeService.getEmployeesByStore(request.getParameter("storeID"));
                break;
                
            case "Update Employee":
                employeeService.updateEmployee(new Employee(request.getParameter("employeeID"), request.getParameter("name"),request.getParameter("surname"),Integer.parseInt(request.getParameter("role")),request.getParameter("password"),request.getParameter("storeID")));
                break;
                
            case "Update Employee Role":
                employeeService.updateEmployeeRole(new Employee(request.getParameter("employeeID"), request.getParameter("name"),request.getParameter("surname"),Integer.parseInt(request.getParameter("role")),request.getParameter("password"),request.getParameter("storeID")));
                break;
            
        }
    }
}
