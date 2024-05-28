package Servlet;

import DBUtil.CookieUtil;
import Entity.Account;
import Entity.Cart;
import Entity.Customer;
import EntityDB.AccountDB;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.HttpCookie;

@WebServlet(name="login",value="/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        Account account = AccountDB.getAccount(user,pass);

        if(account != null) {
            // Tạo session
            HttpSession session = req.getSession();
            // Đẩy acc lên session
            session.setAttribute("account",account);
            Customer customer = CustomerDB.getCustomerByAccount(account);
            session.setAttribute("customer",customer);
            Cookie cookie = new Cookie("accountID",account.getId().toString());
            Cookie cookie1 = new Cookie("customerID",CustomerDB.getCustomerByAccount(account).getId().toString());

            cookie.setMaxAge(86400);
            cookie.setPath("/");
            cookie1.setMaxAge(86400);
            cookie1.setPath("/");

            resp.addCookie(cookie);
            resp.addCookie(cookie1);

            resp.sendRedirect("home");
        }else {
            req.setAttribute("mess","Wrong User or Password !!!!");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

}
