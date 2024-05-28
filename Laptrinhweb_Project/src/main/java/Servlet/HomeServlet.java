package Servlet;

import DBUtil.CookieUtil;
import Entity.*;
import EntityDB.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
@WebServlet(name="home",value="/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        List<Product> productList = ProductDB.getAllProduct();
        List<Category> categoryList = CategoryDB.getAllCategory();
        Product productLast = ProductDB.getLastProduct();
        Cookie[] cookies = req.getCookies();
        String accountID = CookieUtil.getCookieValue(cookies,"accountID");
        if(!accountID.isEmpty()){
            Account account = AccountDB.getAccountByID(Long.parseLong(accountID));
            Customer customer = CustomerDB.getCustomerByAccount(account);
            Cart cart = CartDB.getCartByCustomer(customer);
            HttpSession session = req.getSession();
            // Đẩy account lên session
            session.setAttribute("account",account);
            session.setAttribute("customer",customer);
            session.setAttribute("cart",cart);

        }

        req.setAttribute("listCC",categoryList);
        req.setAttribute("listP",productList);
        req.setAttribute("productLast",productLast);


        // Chuyển dữ liệu đến trang Home.jsp
        req.getRequestDispatcher("Home.jsp").forward(req,resp);
    }
}
