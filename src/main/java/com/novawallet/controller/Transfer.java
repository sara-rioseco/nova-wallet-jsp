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
import com.novawallet.model.dto.ContactDTO;
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

import static java.lang.Integer.parseInt;

@WebServlet(name = "transfer", value = "/transfer")
public class Transfer extends HttpServlet {


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
        if (mail == null) {
            response.sendRedirect("index.jsp");
        } else {
            User user = userService.getUserByEmail(String.valueOf(mail));
            Account account = accountService.getAccountsByOwnerId(user.getId()).get(0);
            Currency currency = currencyService.getCurrencyById(account.getCurrencyId());
            List<Contact> contacts = contactService.getAllContactsByOwnerId((user.getId()));
            List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();

            for (Contact contact : contacts) {
                ContactDTO dto = new ContactDTO(contact, user.getId());
                contactsDTO.add(dto);
            }

            session.setAttribute("name", user.getFirstName());
            session.setAttribute("last", user.getLastName());
            session.setAttribute("mail", user.getEmail());
            session.setAttribute("userId", user.getId());
            session.setAttribute("accountId", account.getId());
            session.setAttribute("currency", currency.getSymbol());
            session.setAttribute("balanceBD", account.getBalance());
            session.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
            session.setAttribute("contacts", contactsDTO);
            request.setAttribute("name", user.getFirstName());
            request.setAttribute("last", user.getLastName());
            request.setAttribute("mail", user.getEmail());
            request.setAttribute("userId", user.getId());
            request.setAttribute("accountId", account.getId());
            request.setAttribute("currency", currency.getSymbol());
            request.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currency.getSymbol(), "USD") ? Locale.US : null).format(account.getBalance()));
            request.setAttribute("balanceBD", account.getBalance());
            request.setAttribute("contacts", contactsDTO);

            try {
                request.getRequestDispatcher("view/transfer.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I'm in doPost here now");
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        int accountId = (int) session.getAttribute("accountId");
        Account senderAccount = accountService.getAccountById(accountId);
        Object name = session.getAttribute("name");
        String amount = req.getParameter("amount");
        int receiverUserId = parseInt(req.getParameter("contact"));
        int receiverAccountId = accountService.getAccountsByOwnerId(receiverUserId).get(0).getId();
        Account receiverAccount = accountService.getAccountById(receiverAccountId);
        BigDecimal BDAmount = new BigDecimal(amount);
        Transaction transaction = null;
        try {
            senderAccount = accountService.getAccountsByOwnerId(user.getId()).get(0);
            boolean resSender = accountService.updateBalance(senderAccount.getId(), BDAmount, TransactionType.transfer, true);
            boolean resReceiver = accountService.updateBalance(receiverAccount.getId(), BDAmount, TransactionType.transfer, false);
            transaction = new Transaction(BDAmount, senderAccount.getCurrencyId(), TransactionType.transfer, user.getId(), senderAccount.getId(), receiverUserId, receiverAccountId);
            boolean transactionRes = transactionService.createTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Error creating deposit: " + e.getMessage());
        }


        assert senderAccount != null;
        req.setAttribute("balance", senderAccount.getBalance());
        session.setAttribute("balance", senderAccount.getBalance());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        req.setAttribute("balance", senderAccount.getBalance());
        session.setAttribute("balance", senderAccount.getBalance());
        req.getRequestDispatcher("home").forward(req, resp);
//        resp.sendRedirect("view/home.jsp");
    }

    public void destroy() {
    }
}
