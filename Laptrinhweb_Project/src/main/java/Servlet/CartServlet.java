package Servlet;

import DBUtil.DBUtil;
import Entity.Cart;
import Entity.CartLine;
import Entity.Customer;
import Entity.Product;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.CustomerDB;
import EntityDB.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cart", value = "/cart")
public class CartServlet extends HttpServlet {
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
        Cart cart = (Cart) session.getAttribute("cart");
        if ("add".equals(action)) {
            addToCart(req, cart);
        } else if ("delete".equals(action)) {
            deletefromcart(req, cart);
        } else if ("addquantity".equals(action)) {
            addQuantity(req, cart);
        } else if ("minusquantity".equals(action)) {
            minusQuantity(req, cart);
        }
        CartDB.update(cart);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }


    private void addToCart(HttpServletRequest req, Cart cart) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        Product product = ProductDB.getProductByID(productID);
        CartLine cartLine = new CartLine(product);
        cart.addCartLine(cartLine);
    }

    private void deletefromcart(HttpServletRequest req, Cart cart) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        cart.removeCartLine(productID);
    }

    private void addQuantity(HttpServletRequest req, Cart cart) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        cart.addQuantityProduct(productID);
    }

    private void minusQuantity(HttpServletRequest req, Cart cart) {
        Long productID = Long.parseLong(req.getParameter("productID"));
        cart.minusQuantityProduct(productID);
    }
}
