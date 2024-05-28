package Servlet;

import DBUtil.DBUtil;
import EntityDB.CartDB;
import EntityDB.CartLineDB;
import EntityDB.CategoryDB;
import EntityDB.ProductDB;
import Entity.Product;
import Entity.Cart;
import Entity.Customer;
import EntityDB.CartDB;
import EntityDB.CustomerDB;
import jakarta.persistence.Entity;
import Entity.CartLine;
import Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            addProduct(req);
        } else if ("delete".equals(action)) {
            deleteProduct(req);
        } else if ("edit".equals(action)) {
            editProduct(req);
        } else if ("loadproduct".equals(action)) {
            loadProduct(req);
            req.getRequestDispatcher("EditProduct.jsp").forward(req, resp);
        } else if ("detail".equals(action)) {
            detailProduct(req);
            req.getRequestDispatcher("Detail.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("manageproduct");
    }

    private void detailProduct(HttpServletRequest req) {
        Long pid = Long.parseLong(req.getParameter("pid"));
        ProductDB productDB = new ProductDB();
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categoryList = categoryDB.getAllCategory();
        Product productLast = productDB.getLastProduct();
        Product product = productDB.getProductByID(pid);
        req.setAttribute("detail", product);
        req.setAttribute("listCC", categoryList);
        req.setAttribute("productLast", productLast);

    }

    private void loadProduct(HttpServletRequest req) {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categoryList = categoryDB.getAllCategory();
        Long pid = Long.parseLong(req.getParameter("pid"));
        Product product = ProductDB.getProductByID(pid);

        req.setAttribute("detail", product);
        req.setAttribute("listCC", categoryList);
    }

    private void editProduct(HttpServletRequest req) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String image = req.getParameter("image");
        String color = req.getParameter("color");
        int size = Integer.parseInt(req.getParameter("size"));
        Double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");

        // Lấy category từ CateID
        Category category = CategoryDB.getCategoryByID(Integer.parseInt(req.getParameter("category")));
        // Tạo một đối tượng Product để cập nhật
        Product productToUpdate = em.find(Product.class, id);// cập nhật sản phẩm theo id

        //  setdata
        productToUpdate.setProductName(name);
        productToUpdate.setBrand(brand);
        productToUpdate.setProductImage(image);
        productToUpdate.setColor(color);
        productToUpdate.setSize(size);
        productToUpdate.setProductCost(price);
        productToUpdate.setDescription(description);

        //Cập nhật
        ProductDB.update(productToUpdate);
    }

    private void deleteProduct(HttpServletRequest req) {
        Long pid = Long.parseLong(req.getParameter("pid"));
        Product product = ProductDB.getProductByID(pid);
        List<CartLine> cartLines = CartLineDB.getCartLinesByProductID(pid);
        for (var item : cartLines) {
            Cart cart = CartDB.getCartByCartLine(item);
            cart.removeCartLine(pid);
            CartDB.update(cart);
        }
        ProductDB.delete(product);
    }

    private void addProduct(HttpServletRequest req) {
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String image = req.getParameter("image");
        String color = req.getParameter("color");
        int size = Integer.parseInt(req.getParameter("size"));
        Double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        // Lấy category từ CateID
        Category category = CategoryDB.getCategoryByID(Integer.parseInt(req.getParameter("category")));
        Product product = new Product(name, brand, image, color, size, price, description, category);
        ProductDB.insert(product);
    }
}
