/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.carolsboutique.clientpos.store.controller;
import java.util.logging.Logger;
import com.carolsboutique.clientpos.restclient.RestClientStore;
import com.carolsboutique.clientpos.store.model.Review;
import com.carolsboutique.clientpos.store.model.Store;
import com.carolsboutique.clientpos.store.service.iStoreService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
@WebServlet(name = "StoreController", urlPatterns = {"/StoreController"})

public class StoreController extends HttpServlet {
private static Logger logger = Logger.getLogger(StoreController.class.getName());
  private iStoreService storeService;

    public StoreController() {
        storeService = new RestClientStore();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("submit")){
            
            case "Login Store":
                HttpSession session = request.getSession();
                RequestDispatcher rd = request.getRequestDispatcher("Employee Login.jsp");
                session.setAttribute("storeID", request.getParameter("storeID"));
                rd.forward(request, response);
            break;
            
               case "REGISTER":
                    String storeID = "STRr";
                for(int i = 0; i<3;i++){
                    storeID += (char)((int)(Math.random()*(88-65)+65)) + (int)(Math.random()*(9-0));
                }
                //TODO abc
                logger.log(Level.WARNING,"storeID = {0}", storeID);
                
                
                storeService.addStore(new Store(storeID, request.getParameter("storeBranch"),
//                        Float.parseFloat(request.getParameter("dailyTarget")),
//                        Float.parseFloat(request.getParameter("monthlyTarget")),
                        0,0,null, null, null));
              RequestDispatcher rda = request.getRequestDispatcher("MainMenu.jsp");
              rda.forward(request, response);
                break;

            case "Add Review":
                storeService.addReview(new Review(request.getParameter("ID"), request.getParameter("storeID"), (request.getParameter("comment")), Integer.parseInt(request.getParameter("rating")), null));
                break;

            case "Delete Review":
                storeService.deleteReview(request.getParameter("ID"));
                break;

            case "Delete Store":
                storeService.deleteStore((request.getParameter("storeID")));
                break;

            case "Get All reviews ":
                storeService.getAllReviews();
                break;

            case "Get review":
                storeService.getReview((request.getParameter("storeID")));
                break;

            case "Get reviews by store":
                storeService.getReviewsByStore(request.getParameter("storeID"));
                break;
                
                case "Get Store":
                storeService.getStore((request.getParameter("storeID")));
                break;
                
                case "Get Stores by Product":
                storeService.getStoresByProduct(request.getParameter("productID"));
                break;
                

        }
    }

   
}
