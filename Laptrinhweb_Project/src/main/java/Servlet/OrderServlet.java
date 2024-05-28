package Servlet;

import Entity.*;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import EntityDB.OrderDB;
import EntityDB.ProductDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "order", value = "/order")
public class OrderServlet extends HttpServlet {
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
        if ("add".equals(action)) {
            addOrder(req);
        } else if ("addnow".equals(action)) {
            buyNow(req);
//            addtocart(req);
//            addOrder(req);
        } else if ("manage".equals(action)) {
            showOrderList(req);
            req.getRequestDispatcher("ManageOrder.jsp").forward(req, resp);
        }
        else if("solveOrder".equals(action)){
            Long orderID = Long.parseLong(req.getParameter("orderID"));
            Order order = OrderDB.getOrderByID(orderID);
            solveOrder(order);
        }
        else if ("myOrder".equals(action)) {
            Customer customer = (Customer) session.getAttribute("customer");
            showMyOrderList(req, customer);
            req.getRequestDispatcher("ManageOrder.jsp").forward(req, resp);
        } else if ("orderdetails".equals(action)) {
            Long orderID = Long.parseLong(req.getParameter("orderid"));
            Order order = OrderDB.getOrderByID(orderID);
            HttpSession session1 = req.getSession();
            session1.setAttribute("order",order);
            req.setAttribute("order",order);
            showOrderDetailList(req, order);
            req.getRequestDispatcher("ManageOrderDetails.jsp").forward(req, resp);

        }
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    private void buyNow(HttpServletRequest req) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        Product product = ProductDB.getProductByID(productID);
        CartLine cartLine = new CartLine(product,1L);
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartLine> cartLineList = cart.getCartLines();
        cart.setCartLines(new ArrayList<CartLine>());
        cart.getCartLines().add(cartLine);
        CartDB.update(cart);
        addOrder(req);
        cart.setCartLines(cartLineList);
        CartDB.update(cart);
    }

    private void solveOrder( Order order) {
        order.setStatus("Processed");
        OrderDB.update(order);
    };



    private void showOrderDetailList(HttpServletRequest req, Order order) {
        List<OrderDetail> orderDetailList = order.getOrderDetails();
        req.setAttribute("listOrderDetails", orderDetailList);
    }

    private void showOrderList(HttpServletRequest req) {
        List<Order> orderList = OrderDB.getAllOrders();
        req.setAttribute("listOrder", orderList);
    }

    private void showMyOrderList(HttpServletRequest req, Customer customer) {

        List<Order> orderList = OrderDB.getOrdersbyCusID(customer.getId());
        req.setAttribute("listOrder", orderList);
    }

    private void addOrder(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("customer");
        Double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
        if (totalPrice != 5.0) {
            List<OrderDetail> listOrderDetails = new ArrayList<OrderDetail>();
            for (var item : cart.getCartLines()) {
                String productName = item.getProduct().getProductName();
                String brand = item.getProduct().getBrand();
                String productImage = item.getProduct().getProductImage();
                String color = item.getProduct().getColor();
                int size = item.getProduct().getSize();
                Double productCost = item.getProduct().getProductCost();
                String description = item.getProduct().getDescription();
                Long quantity = item.getQuantity();
                OrderDetail orderDetail = new OrderDetail(productName, brand, productImage, color, size, productCost, description, quantity);
                listOrderDetails.add(orderDetail);
            }
            cart.setCartLines(new ArrayList<CartLine>());
            CartDB.update(cart);
            Order order = new Order(customer, totalPrice, listOrderDetails);
            OrderDB.insert(order);
        }

    }
}
