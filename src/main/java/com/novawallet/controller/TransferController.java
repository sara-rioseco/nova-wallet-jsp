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
import com.novawallet.model.entity.*;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.DB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import static java.lang.Integer.parseInt;

@WebServlet(name = "transfer", value = "/transfer")
public class TransferController extends HttpServlet {


    private UserService userService;
    private AccountService accountService;
    private CurrencyService currencyService;
    private TransactionService transactionService;
    private ContactService contactService;
    private DB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        db = new DB();
        db.connect();
        UserDAO userDAO = new UserDAOImpl(db);
        AccountDAO accountDAO = new AccountDAOImpl(db);
        CurrencyDAO currencyDAO = new CurrencyDAOImpl(db);
        TransactionDAO transactionDAO = new TransactionDAOImpl(db);
        ContactDAO contactDAO = new ContactDAOImpl(db);
        userService= new UserServiceImpl(userDAO);
        accountService = new AccountServiceImpl(accountDAO, db);
        currencyService = new CurrencyServiceImpl(currencyDAO);
        transactionService = new TransactionServiceImpl(transactionDAO, db);
        contactService = new ContactServiceImpl(contactDAO, db);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        if (mail == null) {
            response.sendRedirect("index.jsp");
        } else {
            User user = userService.getUserByEmail(String.valueOf(mail));
            Account account = accountService.getAccountsByOwnerId(user.getId()).getFirst();
            Currency currency = currencyService.getCurrencyById(account.getCurrencyId());
            List<Contact> contacts = contactService.getAllContactsByOwnerId((user.getId()));
            List<ContactDTO> contactsDTO = new ArrayList<>();

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
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        int accountId = (int) session.getAttribute("accountId");
        Account senderAccount = accountService.getAccountById(accountId);
        String amount = req.getParameter("amount");
        int receiverContactId = parseInt(req.getParameter("contact"));
        int receiverUserId = contactService.getContactUserIdByContactId(receiverContactId);
        int receiverAccountId = accountService.getAccountsByOwnerId(receiverUserId).getFirst().getId();
        Account receiverAccount = accountService.getAccountById(receiverAccountId);
        BigDecimal BDAmount = new BigDecimal(amount);
        Transaction transaction;
        try {
            senderAccount = accountService.getAccountsByOwnerId(user.getId()).getFirst();
            accountService.updateBalance(senderAccount.getId(), BDAmount, TransactionType.transfer, true);
            accountService.updateBalance(receiverAccount.getId(), BDAmount, TransactionType.transfer, false);
            transaction = new Transaction(BDAmount, senderAccount.getCurrencyId(), TransactionType.transfer, user.getId(), senderAccount.getId(), receiverUserId, receiverAccountId);
            transactionService.createTransaction(transaction);
        } catch (Exception e) {
            System.out.println("Error creating transfer: " + e.getMessage());
        }
        assert senderAccount != null;
        req.setAttribute("balance", senderAccount.getBalance());
        session.setAttribute("balance", senderAccount.getBalance());
        req.getRequestDispatcher("home").forward(req, resp);
    }

    public void destroy() {
    }
}
