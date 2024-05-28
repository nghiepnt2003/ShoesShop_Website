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

@WebServlet(name="category",value="/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int CateID = Integer.parseInt(req.getParameter("cid"));
        ProductDB productDB = new ProductDB();
        CategoryDB categoryDB = new CategoryDB();
        List<Product> productListbyCID = productDB.getProductsByCID(CateID);
        List<Category> categoryList = categoryDB.getAllCategory();
        Product productLast = productDB.getLastProduct();

        req.setAttribute("listP",productListbyCID);
        req.setAttribute("listCC",categoryList);
        req.setAttribute("productLast",productLast);
        req.setAttribute("tag",CateID);
        req.getRequestDispatcher("Home.jsp").forward(req,resp);
    }
}
