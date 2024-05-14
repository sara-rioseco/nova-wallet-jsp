package com.novawallet.controller;

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
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@WebServlet(name ="signup", value="/signup")
public class SignUp extends HttpServlet {

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
        response.sendRedirect("signup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String hash = Bcrypt.encode(req.getParameter("password"));
        List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
        User user = new User(firstName, lastName, email, hash);
        User newUser = null;
        Account newAccount = null;
        boolean res = false;
        try {
            this.userService.createUser(user);
            newUser = this.userService.getUserByEmail(email);
            newAccount = new Account(newUser.getId(), 1);
            res = this.accountService.createAccount(newAccount);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (newUser != null && res) {
            req.setAttribute("name", newUser.getFirstName());
            req.setAttribute("last", newUser.getLastName());
            req.setAttribute("mail", newUser.getEmail());
            req.setAttribute("id", newUser.getId());
            req.setAttribute("currency", currencyService.getCurrencyById(1).getSymbol());
            req.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currencyService.getCurrencyById(1).getSymbol(), "USD") ? Locale.US : null).format(newAccount.getBalance()));
            req.setAttribute("transactions", transactionsDTO);
            session.setAttribute("name", newUser.getFirstName());
            session.setAttribute("last", newUser.getLastName());
            session.setAttribute("mail", newUser.getEmail());
            session.setAttribute("userId", newUser.getId());
            session.setAttribute("accountId", accountService.getAccountsByOwnerId(newUser.getId()).get(0).getId());
            session.setAttribute("currency", currencyService.getCurrencyById(1).getSymbol());;
            session.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currencyService.getCurrencyById(1).getSymbol(), "USD") ? Locale.US : null).format(accountService.getAccountsByOwnerId(newUser.getId()).get(0).getBalance()));
            session.setAttribute("transactions", transactionsDTO);
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String url = (newUser != null && newAccount != null) ? "view/home.jsp":"signup.jsp";
        req.getRequestDispatcher(url).forward(req, resp);

    }

    public void destroy() {
    }
}
