package org.perscholas.BankOfAntMerica.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.DAO.AccountDAO;
import org.perscholas.BankOfAntMerica.database.DAO.AccountTransactionDAO;
import org.perscholas.BankOfAntMerica.database.DAO.BranchDAO;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.perscholas.BankOfAntMerica.database.Entity.AccountTransaction;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.form.CreateAccountFormBean;
import org.perscholas.BankOfAntMerica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private BranchDAO branchDao;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private AccountTransactionDAO accountTransactionDAO;


    @GetMapping("/create-account")
    public ModelAndView createAccount(Model model) {
        ModelAndView response = new ModelAndView("users/create");
        model.addAttribute("currentPage", "create");
        response.addObject(model);
        List<Branch> branches = branchDao.findAll();
        response.addObject("branches", branches);
        return response;
    }

    @PostMapping("/create-account")
    public ModelAndView createAccountSubmit(@Valid CreateAccountFormBean form, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();


        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : {} = {}", ((FieldError) error).getField(), error.getDefaultMessage());
            }
            List<Branch> branches = branchDao.findAll();
            response.addObject("branches", branches);
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            response.addObject("currentPage", "create");
            response.setViewName("users/create");
            return response;
        } else {
            // there were no errors so we can create the new user in the database

           User user = userService.createUser(form);
            userService.assignUserRole(form, user);
            authenticatedUserUtils.manualAuthentication(session, form.getEmail(), form.getPassword());
        }
        response.setViewName("redirect:/users/dashboard");
        return response;
    }

    @GetMapping("/dashboard")
    ModelAndView userDashboard(HttpServletRequest request, Model model) {
        ModelAndView response = new ModelAndView("users/dashboard");
        model.addAttribute("currentPage", "dashBoard");
        response.addObject(model);
        User currentUser = authenticatedUserUtils.getCurrentUserObject();
        response.addObject("currentUser", currentUser);
        String url = request.getRequestURI();
        log.debug("User dashboard url : {}", url);
        response.addObject("url", url);
        List<Account> accounts = accountDAO.findAccountsByUserId(currentUser.getId());
        response.addObject("managedAccounts", accounts);

        return response;
    }

    @GetMapping("/account/{id}")
    public ModelAndView userAccount(@PathVariable Integer id, HttpServletRequest request, Model model) {
        ModelAndView response = new ModelAndView("users/accountView");
        User currentUser = authenticatedUserUtils.getCurrentUserObject();
        Account account = accountDAO.findAccountById(id);
        response.addObject("account", account);
        if (account != null) {
            List<AccountTransaction> transactions = accountTransactionDAO.findByAccountId(id);
            List<AccountTransaction> lastTenTransactions = new ArrayList<>();
            int count = 10;
            for(AccountTransaction transaction : transactions.reversed()) {
                if(count != 0){
                    count--;
                }
                if(count == 0){
                    break;
                }
                lastTenTransactions.add(transaction);
            }
            response.addObject("transactions", lastTenTransactions);
        }
        response.addObject("currentUser", currentUser);
        return response;
    }
}