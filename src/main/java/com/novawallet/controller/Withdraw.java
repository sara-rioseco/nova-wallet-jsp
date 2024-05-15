package com.novawallet.controller;

import java.io.*;
import java.math.BigDecimal;

import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.Bcrypt;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "withdraw", value = "/withdraw")
public class Withdraw extends HttpServlet {


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
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        Object balance = session.getAttribute("balance");
        response.sendRedirect("view/withdraw.jsp");
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
            boolean res = accountService.updateBalance(account.getId(), BDAmount, TransactionType.withdrawal);
            transaction = new Transaction(BDAmount, account.getCurrencyId(), TransactionType.withdrawal, user.getId(), account.getId(),user.getId(),account.getId());
            boolean transactionRes = transactionService.createTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Error creating withdrawal: " + e.getMessage());
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
