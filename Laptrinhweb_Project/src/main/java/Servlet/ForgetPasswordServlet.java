package Servlet;

import Entity.Customer;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "forgetpass", value = "/forgetpass")
public class ForgetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String otp = req.getParameter("otp");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        HttpSession session = req.getSession();
        Customer customerForgetPassword = (Customer) session.getAttribute("customerForgetPassword");
        String otpRePassword = (String) session.getAttribute("otpRePassword");
        if (otpRePassword == null || !otp.equals(otpRePassword))
        {
            req.setAttribute("mess", "Wrong OTP !!!!");
        }
        else if (!pass.equals(repass)){
            req.setAttribute("mess", "Pass and repass no matches !!!!");
        }
            else if (pass.equals(repass) && otpRePassword != null && otp.equals(otpRePassword))
        {
            customerForgetPassword.getAccount().setPassword(pass);
            CustomerDB.update(customerForgetPassword);
            req.setAttribute("mess_success", "Successful");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
    }
}
