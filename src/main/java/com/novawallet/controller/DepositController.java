package com.novawallet.controller;

import java.io.*;
import java.math.BigDecimal;
import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.entity.*;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.DB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * The type Deposit controller.
 */
@WebServlet(name = "deposit", value = "/deposit")
public class DepositController extends HttpServlet {

    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;
    private DB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        db = new DB();
        db.connect();
        UserDAO userDAO = new UserDAOImpl(db);
        AccountDAO accountDAO = new AccountDAOImpl(db);
        TransactionDAO transactionDAO = new TransactionDAOImpl(db);
        userService= new UserServiceImpl(userDAO);
        accountService = new AccountServiceImpl(accountDAO, db);
        transactionService = new TransactionServiceImpl(transactionDAO, db);
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
        String amount = req.getParameter("amount");
        BigDecimal BDAmount = new BigDecimal(amount);
        Transaction transaction;
        try {
            account = accountService.getAccountsByOwnerId(user.getId()).getFirst();
            accountService.updateBalance(account.getId(), BDAmount, TransactionType.deposit, true);
            transaction = new Transaction(BDAmount, account.getCurrencyId(), TransactionType.deposit, user.getId(), account.getId(),user.getId(),account.getId());
            transactionService.createTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Error creating deposit: " + e.getMessage());
        }
        assert account != null;
        req.setAttribute("balance", account.getBalance());
        session.setAttribute("balance", account.getBalance());
        resp.setContentType("text/html");
        req.setAttribute("balance", account.getBalance());
        session.setAttribute("balance", account.getBalance());
        req.getRequestDispatcher("home").forward(req, resp);
    }

    public void destroy() {
    }
}
