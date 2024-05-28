package Servlet;

import Entity.Category;
import Entity.Product;
import EntityDB.CategoryDB;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="search",value="/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txtSearch = req.getParameter("txt");
        ProductDB productDB = new ProductDB();
        CategoryDB categoryDB = new CategoryDB();
        List<Product> productsByName = productDB.getProductsByName(txtSearch);
        List<Category> categoryList = categoryDB.getAllCategory();
        Product productLast = productDB.getLastProduct();

        req.setAttribute("txtS",txtSearch);
        req.setAttribute("productLast",productLast);
        req.setAttribute("listCC",categoryList);
        req.setAttribute("listP",productsByName);
        req.getRequestDispatcher("Home.jsp").forward(req,resp);

    }

}
