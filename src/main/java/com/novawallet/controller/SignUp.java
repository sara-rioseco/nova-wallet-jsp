package com.novawallet.controller;

import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.service.UserService;
import com.novawallet.model.service.impl.UserServiceImpl;
import com.novawallet.shared.Bcrypt;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="signup", value="/signup")
public class SignUp extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO= new UserDAOImpl();
        userService= new UserServiceImpl(userDAO);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> hola hola </h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        String hash = "$2a$10$QqnFygUTS9Ki3aYPwGt6T.e9S6OWWPm8TC6mrS8M4li3nOBhKpBSe";
        boolean isPasswordValid = Bcrypt.verify(pass, hash);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        // ------ HTML ------
        out.println("<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">"
                +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">"
                +"<title>NovaWallet</title></head><body data-bs-theme=\"dark\" class=\"container-sm d-flex flex-column justify-content-center align-items-center h-100\">");
        out.println("<main style=\"max-width: 800px\">");

        // ------ Main ------
        out.print("<h1>name: " + mail + ", your pass word is: "+ (isPasswordValid?"valid":"invalid") + "</h1>");


        // ------- Main ends here -------
        out.println("</main><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script></body></html>");
    }

    public void destroy() {
    }
}
