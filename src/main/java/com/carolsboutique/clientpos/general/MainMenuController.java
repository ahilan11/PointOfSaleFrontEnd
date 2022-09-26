package com.carolsboutique.clientpos.general;

import com.carolsboutique.clientpos.store.model.Store;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MainMenuController", urlPatterns = {"/MainMenuController"})
public class MainMenuController extends HttpServlet {
    private Store loggedInStore;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch("submit"){
            case "getHomePage":
                
                break;
            case "getLookupPage":
                
                break;
            case "getSalePage":
                
                break;
            case "getStockPage":
                
                break;
            case "getRegisterEmpPage":
                
                break;
            case "getRegisterStorePage":
                request.setAttribute("loggedInStore", loggedInStore);
                request.getRequestDispatcher("RegisterEmployee.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
