package Servlet;

import DBUtil.DBUtil;
import Entity.*;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import EntityDB.OrderDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "customer", value = "/customer")
public class CustomerServlet extends HttpServlet {
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
        String action = req.getParameter("action");
        if ("loadCustomer".equals(action)) {
            loadCustomer(req);
            req.getRequestDispatcher("EditCustomer.jsp").forward(req, resp);
        } else if ("deleteCustomer".equals(action)) {
            deleteCustomer(req);
        } else if ("editCustomer".equals(action)) {
            editCustomer(req);
        }
        resp.sendRedirect("managecustomer");

    }

    private void editCustomer(HttpServletRequest req) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String deliveryAddress = req.getParameter("deliveryAddress");
        Customer customerToUpdate = em.find(Customer.class, id);// cập nhật sản phẩm theo id
        // Tạo một đối tượng Customer để cập nhật Customer
        Customer customer = CustomerDB.getCustomerByID(id);
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

    private void deleteCustomer(HttpServletRequest req) {
        Long cusid = Long.parseLong(req.getParameter("cusid"));
        Customer customer = CustomerDB.getCustomerByID(cusid);
        // Do Mỗi khi tạo ra một khách hàng thì sẽ tạo ra một cart cho khách hàng đó
        Cart cart = CartDB.getCartByCustomer(customer);
        cart.setCartLines(new ArrayList<CartLine>());
        CartDB.update(cart);
        // Nên phải xóa cart trước khi xóa khách hàng để đảm bảo không bị lỗi
        CartDB.delete(cart);
        List<Order> orderList = OrderDB.getOrdersbyCusID(cusid);
        for (var item:orderList) {
            item.setOrderDetails(new ArrayList<OrderDetail>());
            OrderDB.update(item);
            OrderDB.delete(item);
        }
        CustomerDB.delete(customer);
    }

    private void loadCustomer(HttpServletRequest req) {
        Long cusid = Long.parseLong(req.getParameter("cusid"));
        Customer customer = CustomerDB.getCustomerByID(cusid);
        req.setAttribute("detail", customer);
    }
}
