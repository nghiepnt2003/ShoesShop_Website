package Servlet;

import DBUtil.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name="logout",value="/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        //Tạo session
        HttpSession session = req.getSession();
        // Khi Logout thì remove session "acc" trên session
//        session.removeAttribute("acc");
        session.removeAttribute("account");
        session.removeAttribute("customer");
        session.removeAttribute("cart");

        Cookie[] cookies = req.getCookies();
        Cookie cookie = CookieUtil.getCookie(cookies,"accountID");
        if(cookie != null)
        {
            CookieUtil.removeCookie(cookie);
        }
        resp.addCookie(cookie);
        resp.sendRedirect("home");
    }
}
