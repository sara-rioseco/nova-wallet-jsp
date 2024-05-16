package com.novawallet.controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.dto.TransactionDTO;
import com.novawallet.model.entity.*;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.Bcrypt;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "deposit", value = "/deposit")
public class Deposit extends HttpServlet {

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
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("view/deposit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        int accountId = (int) session.getAttribute("accountId");
        Account account = accountService.getAccountById(accountId);
        Object name = session.getAttribute("name");
        String amount = req.getParameter("amount");
        BigDecimal BDAmount = new BigDecimal(amount);
        Transaction transaction = null;
        try {
            account = accountService.getAccountsByOwnerId(user.getId()).get(0);
            boolean res = accountService.updateBalance(account.getId(), BDAmount, TransactionType.deposit, true);
            transaction = new Transaction(BDAmount, account.getCurrencyId(), TransactionType.deposit, user.getId(), account.getId(),user.getId(),account.getId());
            boolean transactionRes = transactionService.createTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Error creating deposit: " + e.getMessage());
        }


        assert account != null;
        req.setAttribute("balance", account.getBalance());
        session.setAttribute("balance", account.getBalance());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        req.setAttribute("balance", account.getBalance());
        session.setAttribute("balance", account.getBalance());
        req.getRequestDispatcher("home").forward(req, resp);
//        resp.sendRedirect("view/home.jsp");
    }

    public void destroy() {
    }
}
