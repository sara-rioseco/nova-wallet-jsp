package com.novawallet.controller;

import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class Logout extends HttpServlet {

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

    public void destroy() {
    }
}
