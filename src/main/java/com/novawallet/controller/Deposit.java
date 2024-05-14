package com.novawallet.controller;

import java.io.*;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.AccountDAOImpl;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.AccountService;
import com.novawallet.model.service.UserService;
import com.novawallet.model.service.impl.AccountServiceImpl;
import com.novawallet.model.service.impl.UserServiceImpl;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO= new UserDAOImpl();
        userService= new UserServiceImpl(userDAO);
        accountDAO = new AccountDAOImpl();
        accountService = new AccountServiceImpl(accountDAO);
    }

}
