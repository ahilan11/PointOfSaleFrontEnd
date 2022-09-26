package com.carolsboutique.clientpos.product.controller;

import com.carolsboutique.clientpos.product.model.Category;
import com.carolsboutique.clientpos.product.model.InterBranchTransfer;
import com.carolsboutique.clientpos.product.model.KeepAside;
import com.carolsboutique.clientpos.product.model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.carolsboutique.clientpos.restclient.RestClientProduct;
import com.carolsboutique.clientpos.restclient.RestClientStore;
import com.carolsboutique.clientpos.store.model.Store;
import jakarta.servlet.RequestDispatcher;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
    private RestClientProduct prodService;
    private RestClientStore storeService;
    private Product product;

    public ProductController() {
        prodService = new RestClientProduct();
        storeService = new RestClientStore();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getParameter("submit")){
           
            case "getIbtPage":
                List<Store> storesWithProd = storeService.getStoresByProduct(product.getId());
                request.setAttribute("storesWithProd", storesWithProd);
                request.getRequestDispatcher("createIBT.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getParameter("submit")){
     case "FIND PRODUCT":
                Product pr = new Product();
                String id = request.getParameter("productID");
                pr.setId(id);
                System.out.println(id);
                product = prodService.getProduct(pr);
                System.out.println("ok");
                if(product!=null){
                    System.out.println("better");
                    request.setAttribute("productID", product.getId());
                    request.setAttribute("productName", product.getName());
                    request.setAttribute("description",product.getDescription());
                    request.setAttribute("size", product.getSize());
                    request.setAttribute("price", product.getPrice());
                  RequestDispatcher rd =   request.getRequestDispatcher("LookUpProduct.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("lookupResp", "That product does not seem to exist!");
                   RequestDispatcher rd =  request.getRequestDispatcher("LookUpProduct.jsp");
                    rd.forward(request, response);
                }
                break;
                 case "SEARCH FOR STORES":
               List<Store> stores =  prodService.getStoresByProduct(request.getParameter("productID"));
                request.getSession(false).setAttribute("storesThatHaveProduct",stores);
                RequestDispatcher rdr = request.getRequestDispatcher("RequestIBT.jsp");
                rdr.forward(request, response);
                break;
            case "REQUEST IBT":
                 String ibtID = "IBT";
                   for(int i = 0; i<8;i++){
                    ibtID += (char)((int)(Math.random()*(88-65)+65)) + (int)(Math.random()*(9-0));
                }
                   InterBranchTransfer ibt = new InterBranchTransfer();
                   ibt.setIbtID(ibtID);
                prodService.addIBT(ibt);
                break;
            case "Add Category":
                prodService.addCategory(new Category(request.getParameter("ID"), request.getParameter("name")));
                break;
            case "Add Ibt":
                prodService.addIBT(new InterBranchTransfer(request.getParameter("IbtID"), request.getParameter("productID"), request.getParameter("toStore"), request.getParameter("fromStore"), Boolean.parseBoolean(request.getParameter("approved")), Boolean.parseBoolean(request.getParameter("received")), request.getParameter("phonenumber"), null, null, null, null));
                break;

            case "Add Keep Aside":
                prodService.addKeepAside(new KeepAside(request.getParameter("ID"), request.getParameter("productID"), request.getParameter("storeID"), request.getParameter("customerEmail"), null, null));
                break;
                
            case "Add Product":
                prodService.addProduct(new Product(request.getParameter("ID"), request.getParameter("name"), request.getParameter("description"), request.getParameter("size"), Double.parseDouble(request.getParameter("price")), Boolean.parseBoolean(request.getParameter("onSale")),Double.parseDouble(request.getParameter("onSalePrice"))));
                break;
                
            case "Approve Ibt":
                prodService.approveIbt(new InterBranchTransfer(request.getParameter("IbtID"), request.getParameter("productID"), request.getParameter("toStore"), request.getParameter("fromStore"), Boolean.parseBoolean(request.getParameter("approved")), Boolean.parseBoolean(request.getParameter("received")), request.getParameter("phonenumber"), null, null, null, null));
             break;
             
            case "Delete Category":
                Category c = new Category();
                c.setCategoryID(request.getParameter("categoryID"));
                prodService.deleteCategory(c);
                break;
                
            case "Delete Ibt":
                InterBranchTransfer ibtrans = new InterBranchTransfer();
                ibtrans.setIbtID(request.getParameter("IbtID"));
                prodService.deleteIbt(ibtrans);
                break;
                
            case "Delete Keep Aside":
                KeepAside kp = new KeepAside();
                kp.setKeepAsideID(request.getParameter("KeepAsideID"));
                prodService.deleteKeepAside(kp);
                break;
                
            case "Delete Product":
                Product p = new Product();
                p.setId(request.getParameter("productID"));
                prodService.deleteProduct(p);
                break;
                
            case "Get All Categories":
                prodService.getAllCategories();
                break;
                
            case "Get All Ibts":
                prodService.getAllIbts();
                break;
                
            case "Get All Keep Asides":
                prodService.getAllKeepAsides();
                break;
                
            case "Get All Products":
                prodService.getAllProducts();
                break;
                
            case "Get Category":
                Category cat = new Category();
                cat.setCategoryID(request.getParameter("categoryID"));
                prodService.getCategory(cat);
                break;
            case "Get Ibt":
                InterBranchTransfer ibtd = new InterBranchTransfer();
                ibtd.setIbtID(request.getParameter("ibtID"));
                prodService.getIbt(ibtd);
                break;
                
            case "Get Ibts By Store":
                Store store = new Store();
                store.setStoreID(request.getParameter("storeID"));
                prodService.getIbtsByStore(store);
                break;
                
            case "Get Product":
                Product prd = new Product();
                prd.setId(request.getParameter("productID"));
                prodService.getProduct(prd);
                break;
                
            case "Get Products by Category":
                 Category cate = new Category();
                cate.setCategoryID(request.getParameter("categoryID"));
                prodService.getProductsByCategory((cate));
                break;
                
            case "Get Products By Store":
                Store st = new Store();
                st.setStoreID(request.getParameter("storeID"));
                prodService.getProductsByStore(st);
                break;
                
            case "Receive Ibt":
                prodService.receiveIbt(new InterBranchTransfer(request.getParameter("IbtID"), request.getParameter("productID"), request.getParameter("toStore"), request.getParameter("fromStore"), Boolean.parseBoolean(request.getParameter("approved")), Boolean.parseBoolean(request.getParameter("received")), request.getParameter("phonenumber"), null, null, null, null));
    break;
    
            case "Update Category":
                prodService.updateCategory(new Category(request.getParameter("ID"), request.getParameter("name")));
              break;
                
            case "Update Ibt":
                prodService.updateIbt(new InterBranchTransfer(request.getParameter("IbtID"), request.getParameter("productID"), request.getParameter("toStore"), request.getParameter("fromStore"), Boolean.parseBoolean(request.getParameter("approved")), Boolean.parseBoolean(request.getParameter("received")), request.getParameter("phonenumber"), null, null, null, null));
    break;
    
            case "Update Keep Aside":
                prodService.updateKeepAside(new KeepAside(request.getParameter("ID"), request.getParameter("productID"), request.getParameter("storeID"), request.getParameter("customerEmail"), null, null));
             break;
             
            case "Update Product":
                prodService.updateProduct(new Product(request.getParameter("ID"), request.getParameter("name"), request.getParameter("description"), request.getParameter("size"), Double.parseDouble(request.getParameter("price")), Boolean.parseBoolean(request.getParameter("onSale")),Double.parseDouble(request.getParameter("onSalePrice"))));
               break;
               
               
               
               
    }

    }
}
