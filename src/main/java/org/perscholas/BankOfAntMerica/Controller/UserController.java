package org.perscholas.BankOfAntMerica.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.*;
import lombok.extern.slf4j.*;
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
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

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

        // homework if you want - check to make sure the email does not already exist
        // this is a great case the custom annotation that we made

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
            if(form.getRole() == null){
                form.setRole("USER");
            }
            userService.createUser(form);
            userService.assignUserRole(form);
            authenticatedUserUtils.manualAuthentication(session, form.getUsername(), form.getPassword());
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

}