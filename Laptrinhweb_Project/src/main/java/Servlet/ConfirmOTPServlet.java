package Servlet;

import DBUtil.MailUtil;
import Entity.Customer;
import EntityDB.CustomerDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "otp", value = "/otp")
public class ConfirmOTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String from = "nghiepnt2003@gmail.com";
        String subject = "Shoes Store";
        String to = req.getParameter("email");
        String name = req.getParameter("name");

        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        String body = "";
        if (action.equals("register")) {
            body = createRegisterMail(req, session);
        } else if ("forget".equals(action)) {
            Customer customer = CustomerDB.getCustomerByMail(name,to);
            if (customer != null && checkMatchCustomer(customer, name)) {
                session.setAttribute("customerForgetPassword", customer);
                body = createRepasswordrMail(session);
            } else {
                resp.getWriter().write("Wrong UserName or Email");
                return;
            }
        }
        try {
            MailUtil.sendMail(to, from, subject, body, true);
            session.setMaxInactiveInterval(300);
            resp.getWriter().write("Send OTP to your Email");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkMatchCustomer(Customer customer, String name) {
        return customer.getAccount().getUserName().equals(name);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(900000) + 100000;
        return String.valueOf(otp);
    }

    private String createRepasswordrMail(HttpSession session) {
        String otp = generateOtp();
        session.setAttribute("otpRePassword", otp);
        return "<!DOCTYPE html>"
                + "<html lang=\"vi\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "    <title>Xác nhận OTP</title>"
                + " <style>\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 14px;\n" +
                "            color: #333333;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "    \n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            border: 5px solid #39c6b9; \n" +
                "            border-radius: 10px; \n" +
                "        }\n" +
                "    \n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "    \n" +
                "       \n" +
                "        h1 {\n" +
                "            color: 39c6b9;\n" +
                "        }\n" +
                "    \n" +
                "        p {\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "    \n" +
                "        a {\n" +
                "            color: #0099ff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>"
                + "</head>"
                + "<body style=\"font-family: Arial, sans-serif; padding: 20px;\">"
                + "  <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <h1>Shoes Store</h1>\n" +
                "            <p>Xin chào. Dưới đây là mã OTP mới để hoàn thành quá trình đặt lại mật khẩu của bạn:</p>\n" +
                "            <p><strong style=\"color: #da4f25;\">" + otp + "</strong></p>\n" +
                "            <p>Vui lòng nhập mã OTP này vào trang web của chúng tôi để xác nhận việc đặt lại mật khẩu.</p>\n" +
                "            <p>Nếu bạn không yêu cầu việc đặt lại mật khẩu, vui lòng liên hệ ngay với chúng tôi.</p>\n" +
                "            <p>Cảm ơn bạn đã sử dụng web của chúng tôi!</p>\n" +
                "            <p>Trân trọng,</p>\n" +
                "            <p>Shoes Store</p>\n" +
                "        </div>\n" +
                "    </div>"
                + "</body>"
                + "</html>";
    }

    private String createRegisterMail(HttpServletRequest req, HttpSession session) {
        String name = req.getParameter("name");
        String otp = generateOtp();
        session.setAttribute("otpRegister", otp);
        return "<!DOCTYPE html>"
                + "<html lang=\"vi\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "    <title>Xác nhận OTP</title>"
                + " <style>\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 14px;\n" +
                "            color: #333333;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "    \n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            border: 5px solid #39c6b9; \n" +
                "            border-radius: 10px; \n" +
                "        }\n" +
                "    \n" +
                "    \n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "    \n" +
                "       \n" +
                "        h1 {\n" +
                "            color: #39c6b9;\n" +
                "        }\n" +
                "    \n" +
                "        p {\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "    \n" +
                "        a {\n" +
                "            color: #0099ff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>"
                + "</head>"
                + "<body style=\"font-family: Arial, sans-serif; padding: 20px;\">"
                + "  <div class=\"container\">\n" +
                "        <div class=\"content\">\n" +
                "            <h1>Shoes Store</h1>\n" +
                "            <p>Xin chào, " + name + "!</p>\n" +
                "            <p>Cảm ơn bạn đã đăng ký tại Shoes Store. Đây là mã OTP của bạn để hoàn thành quá trình đăng ký:</p>\n" +
                "            <p><strong style=\"color: #da4f25;\">" + otp + "</strong></p>\n" +
                "            <p>Vui lòng nhập mã OTP này vào trang web của chúng tôi để xác nhận đăng ký của bạn.</p>\n" +
                "            <p>Cảm ơn bạn đã sử dụng web của chúng tôi!</p>\n" +
                "            <p>Trân trọng,</p>\n" +
                "            <p>Team Shoes Store</p>\n" +
                "        </div>\n" +
                "    </div>"
                + "</body>"
                + "</html>";
    }
}
