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
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        if (mail == null) {
            response.sendRedirect("index.jsp");
        }
        else {
            User user = userService.getUserByEmail(String.valueOf(mail));
            Account account = accountService.getAccountsByOwnerId(user.getId()).get(0);
            Currency currency = currencyService.getCurrencyById(account.getCurrencyId());
            List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());
            List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();

            for (Transaction transaction : transactions) {
                TransactionDTO dto = new TransactionDTO(transaction, user.getId());
                transactionsDTO.add(dto);
            }

            session.setAttribute("name", user.getFirstName());
            session.setAttribute("last", user.getLastName());
            session.setAttribute("mail", user.getEmail());
            session.setAttribute("userId", user.getId());
            session.setAttribute("accountId", account.getId());
            session.setAttribute("currency", currency.getSymbol());
            session.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
            session.setAttribute("transactions", transactionsDTO);
            request.setAttribute("name", user.getFirstName());
            request.setAttribute("last", user.getLastName());
            request.setAttribute("mail", user.getEmail());
            request.setAttribute("userId", user.getId());
            request.setAttribute("accountId", account.getId());
            request.setAttribute("currency", currency.getSymbol());
            request.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
            request.setAttribute("transactions", transactionsDTO);

            try {
                request.getRequestDispatcher("view/home.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        if (pass != null){
            User user = userService.getUserByEmail(mail) != null ? userService.getUserByEmail(mail) : userService.getUserByEmail(String.valueOf(session.getAttribute("mail")));
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
            if (isPasswordValid) {
                session.setAttribute("name", user.getFirstName());
                session.setAttribute("last", user.getLastName());
                session.setAttribute("mail", user.getEmail());
                session.setAttribute("userId", user.getId());
                session.setAttribute("accountId", account.getId());
                session.setAttribute("currency", currency.getSymbol());
                session.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
                session.setAttribute("transactions", transactionsDTO);
                req.setAttribute("name", user.getFirstName());
                req.setAttribute("last", user.getLastName());
                req.setAttribute("mail", user.getEmail());
                req.setAttribute("userId", user.getId());
                req.setAttribute("accountId", account.getId());
                req.setAttribute("currency", currency.getSymbol());
                req.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
                req.setAttribute("transactions", transactionsDTO);
            }
            String url = (isPasswordValid) ? "view/home.jsp" : "index.jsp";
            req.getRequestDispatcher(url).forward(req, resp);
        } else {
            resp.sendRedirect("home");
        }
    }

    public void destroy() {
    }
}