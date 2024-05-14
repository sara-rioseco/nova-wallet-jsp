package com.novawallet.controller;

import java.io.*;

import com.novawallet.model.dao.TransactionDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.TransactionDAOImpl;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.TransactionService;
import com.novawallet.model.service.UserService;
import com.novawallet.model.service.impl.TransactionServiceImpl;
import com.novawallet.model.service.impl.UserServiceImpl;
import com.novawallet.shared.Bcrypt;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "transactions", value = "/transactions")
public class Transactions extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;
    private TransactionService transactionService;
    private TransactionDAO transactionDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO= new UserDAOImpl();
        userService= new UserServiceImpl(userDAO);
        transactionDAO = new TransactionDAOImpl();
        transactionService = new TransactionServiceImpl(transactionDAO);
    }
}
