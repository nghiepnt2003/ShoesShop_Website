package Servlet;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Customer;
import EntityDB.CustomerDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("customer") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }
        Customer customer = (Customer) session.getAttribute("customer");
        String action = req.getParameter("action");
        if ("loadProfile".equals(action)) {
            loadProfile(req, customer);
            req.getRequestDispatcher("Profile.jsp").forward(req, resp);
        } else if ("editProfile".equals(action)) {
            updateProFile(req, customer);
            resp.sendRedirect("home");
        }
    }

    private void updateProFile(HttpServletRequest req, Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String deliveryAddress = req.getParameter("deliveryAddress");

        // Tạo một đối tượng Customer để cập nhật Customer
        Customer customerToUpdate = em.find(Customer.class, id);// cập nhật sản phẩm theo id


        // Tạo một đối tượng Account để cập nhật Account của Customer
        Account accountToUpdate = em.find(Account.class, customer.getAccount().getId());

        //  setdata
        accountToUpdate.setUserName(username);
        accountToUpdate.setPassword(password);

        customerToUpdate.setName(name);
        customerToUpdate.setPhone(phone);
        customerToUpdate.setMail(mail);
        customerToUpdate.setDeliveryAddress(deliveryAddress);
        customerToUpdate.setAccount(accountToUpdate);

        //Cập nhật
        // update customer thì tự động update account vì có lan truyền
        CustomerDB.update(customerToUpdate);
    }

    private void loadProfile(HttpServletRequest req, Customer customer) {

        req.setAttribute("detail", customer);
    }
}
