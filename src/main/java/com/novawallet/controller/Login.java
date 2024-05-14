package com.novawallet.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.dto.TransactionDTO;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Currency;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.Bcrypt;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;
    private AccountService accountService;
    private AccountDAO accountDAO;
    private CurrencyService currencyService;
    private CurrencyDAO currencyDAO;
    private TransactionService transactionService;
    private TransactionDAO transactionDAO;
    private ContactService contactService;
    private ContactDAO contactDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO= new UserDAOImpl();
        userService= new UserServiceImpl(userDAO);
        accountDAO = new AccountDAOImpl();
        accountService = new AccountServiceImpl(accountDAO);
        currencyDAO = new CurrencyDAOImpl();
        currencyService = new CurrencyServiceImpl(currencyDAO);
        transactionDAO = new TransactionDAOImpl();
        transactionService = new TransactionServiceImpl(transactionDAO);
        contactDAO = new ContactDAOImpl();
        contactService = new ContactServiceImpl(contactDAO);
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

        User user = userService.getUserByEmail(mail);
        Account account = accountService.getAccountsByOwnerId(user.getId()).get(0);
        Currency currency = currencyService.getCurrencyById(account.getCurrencyId());
        List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());
        List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
        for (Transaction transaction : transactions) {
            TransactionDTO dto = new TransactionDTO(transaction);
            transactionsDTO.add(dto);
        }

        String storedHash = user.getPassword();
        boolean isPasswordValid = Bcrypt.verify(pass, storedHash);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if ( isPasswordValid) {
            req.setAttribute("name", user.getFirstName());
            req.setAttribute("last", user.getLastName());
            req.setAttribute("mail", user.getEmail());
            req.setAttribute("id", user.getId());
            req.setAttribute("currency", currency.getSymbol());
            req.setAttribute("balance", account.getBalance());
            req.setAttribute("transactions", transactionsDTO);
        }
        String url = (isPasswordValid)?"view/home.jsp":"index.jsp";
        req.getRequestDispatcher(url).forward(req, resp);

        // resp.sendRedirect(url);


        // ------ HTML ------
        //out.println("<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">"
        //        +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">"
        //        +"<title>NovaWallet</title></head><body data-bs-theme=\"dark\" class=\"container-sm d-flex flex-column justify-content-center align-items-center h-100\">");
        // out.println("<main style=\"max-width: 800px\">");

        // ------ Main ------
        // out.print("<h1>name: " + mail + ", your pass word is: "+ (isPasswordValid?"valid":"invalid") + "</h1>");


        // ------- Main ends here -------
       // out.println("</main><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script></body></html>");
    }

    public void destroy() {
    }
}