package Servlet;

import Entity.Account;
import Entity.Category;
import Entity.Product;
import EntityDB.CategoryDB;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="manageproduct",value="/manageproduct")
public class ManageProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        CategoryDB categoryDB = new CategoryDB();
        List<Product> productList = ProductDB.getAllProduct();
        List<Category> categoryList = categoryDB.getAllCategory();

        req.setAttribute("listCC",categoryList);
        req.setAttribute("listP",productList);



        //Phân trang
        int currentPage = 1; // Trang hiện tại
        int recordsPerPage = 5; // Số lượng sản phẩm trên mỗi trang

        if (req.getParameter("page") != null) {
                currentPage = Integer.parseInt(req.getParameter("page"));
        }
        // offset là vị trí index để select trong products
        int offset = (currentPage - 1) * recordsPerPage;

        List<Product> products = ProductDB.selectProductsByOffset(offset, recordsPerPage);
        int totalProducts = ProductDB.getTotalProducts(); // Tổng số sản phẩm

        int totalPages = (int) Math.ceil((double) totalProducts / recordsPerPage);

        req.setAttribute("products", products);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("ManagerProduct.jsp").forward(req,resp);

    }
}
