/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.carolsboutique.clientpos.report.servlet;

import com.carolsboutique.clientpos.product.model.Product;
import com.carolsboutique.clientpos.report.model.Report;
import com.carolsboutique.clientpos.report.service.iReportGenerator;
import com.carolsboutique.clientpos.restclient.RestClientReport;
import com.carolsboutique.clientpos.store.model.Store;
import java.io.IOException;
import java.time.Month;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author patri
 */
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {
    private final iReportGenerator reportGenerator;

    public ReportServlet() {
        reportGenerator = new RestClientReport();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
    }

}
