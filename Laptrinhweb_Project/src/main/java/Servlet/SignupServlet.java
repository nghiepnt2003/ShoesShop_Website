package Servlet;

import Entity.Account;
import Entity.Cart;
import Entity.Customer;
import EntityDB.AccountDB;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String otp = req.getParameter("otp");

        HttpSession session = req.getSession();

        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String otpRegister = (String) session.getAttribute("otpRegister");
        String deliveryAddress = req.getParameter("deliveryAddress");
        if (AccountDB.getAccountbyAccountName(user) != null) {
            req.setAttribute("mess", "Account already exists !!!!");
        }
        if (AccountDB.getAccount(user, pass) != null) {
            req.setAttribute("mess", "Account already exists !!!!");
        } else if (otpRegister == null || !otp.equals(otpRegister)) {
            req.setAttribute("mess", "Wrong OTP !!!!");
        } else if (pass.equals(repass) && otpRegister != null && otp.equals(otpRegister)) {
            Account account = new Account(user, pass, false);
            Customer customer = new Customer(name, phone, email, deliveryAddress, account);
            CustomerDB.insert(customer);
            Cart cart = new Cart(customer);
            CartDB.insert(cart);
            req.setAttribute("mess_success", "Account registration successful");
        }
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }
}
