package com.novawallet.controller;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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

@WebServlet(name = "login", value = "/home")
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
        response.sendRedirect("index.jsp");
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
            TransactionDTO dto = new TransactionDTO(transaction, user.getId());
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
            req.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
            req.setAttribute("transactions", transactionsDTO);
        }
        String url = (isPasswordValid)?"view/home.jsp":"index.jsp";
        req.getRequestDispatcher(url).forward(req, resp);

    }

    public void destroy() {
    }
}