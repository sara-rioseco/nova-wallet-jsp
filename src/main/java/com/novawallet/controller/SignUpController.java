package com.novawallet.controller;

import com.novawallet.model.dao.*;
import com.novawallet.model.dao.impl.*;
import com.novawallet.model.dto.TransactionDTO;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.*;
import com.novawallet.model.service.impl.*;
import com.novawallet.shared.Bcrypt;
import com.novawallet.shared.DB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * The type Sign up controller.
 */
@WebServlet(name ="signup", value="/signup")
public class SignUpController extends HttpServlet {

    private UserService userService;
    private AccountService accountService;
    private CurrencyService currencyService;
    private DB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        db = new DB();
        db.connect();
        UserDAO userDAO = new UserDAOImpl(db);
        AccountDAO accountDAO = new AccountDAOImpl(db);
        CurrencyDAO currencyDAO = new CurrencyDAOImpl(db);
        userService= new UserServiceImpl(userDAO);
        accountService = new AccountServiceImpl(accountDAO, db);
        currencyService = new CurrencyServiceImpl(currencyDAO);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("signup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String hash = Bcrypt.encode(Bcrypt.encoder, req.getParameter("password"));
        List<TransactionDTO> transactionsDTO = new ArrayList<>();
        User user = new User(firstName, lastName, email, hash);
        User newUser = null;
        Account newAccount = null;
        boolean res = false;
        try {
            this.userService.createUser(user);
            newUser = this.userService.getUserByEmail(email);
            newAccount = new Account(newUser.getId(), 1);
            res = this.accountService.createAccount(newAccount);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (newUser != null && res) {
            req.setAttribute("name", newUser.getFirstName());
            req.setAttribute("last", newUser.getLastName());
            req.setAttribute("mail", newUser.getEmail());
            req.setAttribute("id", newUser.getId());
            req.setAttribute("currency", currencyService.getCurrencyById(1).getSymbol());
            req.setAttribute("balanceBD", accountService.getAccountById(accountService.getAccountsByOwnerId(newUser.getId()).getFirst().getId()).getBalance());
            req.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currencyService.getCurrencyById(1).getSymbol(), "USD") ? Locale.US : null).format(newAccount.getBalance()));
            req.setAttribute("transactions", transactionsDTO);
            session.setAttribute("name", newUser.getFirstName());
            session.setAttribute("last", newUser.getLastName());
            session.setAttribute("mail", newUser.getEmail());
            session.setAttribute("userId", newUser.getId());
            session.setAttribute("accountId", accountService.getAccountsByOwnerId(newUser.getId()).getFirst().getId());
            session.setAttribute("currency", currencyService.getCurrencyById(1).getSymbol());
            session.setAttribute("balanceBD", accountService.getAccountById(accountService.getAccountsByOwnerId(newUser.getId()).getFirst().getId()).getBalance());
            session.setAttribute("balance", NumberFormat.getCurrencyInstance(Objects.equals(currencyService.getCurrencyById(1).getSymbol(), "USD") ? Locale.US : null).format(accountService.getAccountsByOwnerId(newUser.getId()).getFirst().getBalance()));
            session.setAttribute("transactions", transactionsDTO);
        }
        resp.setContentType("text/html");
        String url = (newUser != null && newAccount != null) ? "view/home.jsp":"signup.jsp";
        req.getRequestDispatcher(url).forward(req, resp);

    }

    public void destroy() {
    }
}
