package org.perscholas.BankOfAntMerica.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Config.YourInteceptor;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.Security.UserDetailsServiceImpl;
import org.perscholas.BankOfAntMerica.database.DAO.*;
import org.perscholas.BankOfAntMerica.database.Entity.*;
import org.perscholas.BankOfAntMerica.form.CreateAccountFormBean;
import org.perscholas.BankOfAntMerica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
@Autowired
    YourInteceptor yourInteceptor;
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;
    @Autowired
    private BranchDAO branchDAO;
    @Autowired
    private AccountTransactionDAO accountTransactionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpServletRequest request) {


        ModelAndView response = new ModelAndView("admin/dashboard");
        boolean isSafari = userService.isSafari(request);
        response.addObject("isSafari", isSafari);
        User user = authenticatedUserUtils.getCurrentUserObject();
        response.addObject("user", user);
        Branch branch = branchDAO.findBranchById(user.getHomeBranch());


        List<AccountTransaction> accountTransactions = accountTransactionDAO.findByBranchId(branch.getId());
        List<AccountTransaction> shortenedListAccounts = new ArrayList<>(5);
        int count = 6;
        for(AccountTransaction transaction : accountTransactions) {
            count--;
            if(count == 0){
                break;
            }
            shortenedListAccounts.add(transaction);
        }
        response.addObject("accountTransactions", shortenedListAccounts);

        List<Account> managedAccounts = accountDAO.findByBranchId(branch.getId());
        response.addObject("managedAccounts", managedAccounts);

        return response;
    }

    @GetMapping("/searchTool/accounts")
    public ModelAndView searchToolAccounts(@RequestParam(required = false) String term, Model model, HttpServletRequest request) {
        ModelAndView response = new ModelAndView("admin/searchTool");
        model.addAttribute("currentPage", "searchAccount");
        response.addObject(model);
        String accountView = "yes";
        response.addObject("accountView", accountView);
        boolean isSafari = userService.isSafari(request);
        response.addObject("isSafari", isSafari);
        if (term != null) {
            List<Account> searchedAccount = accountDAO.findAllByCustomerTerm(term);
            List<Account> shortenedListAccounts = new ArrayList<>(10);
            int count = 10;
            for(Account user : searchedAccount) {
                count--;
                if(count == 0){
                    break;
                }
                shortenedListAccounts.add(user);
            }
            response.addObject("foundAccounts", shortenedListAccounts);
            response.addObject("term", term);
        }
        return response;
    }

    @GetMapping("/searchTool/users")
    public ModelAndView searchToolUsers(@RequestParam(required = false) String term, HttpServletRequest request, Model model) {
        ModelAndView response = new ModelAndView("admin/searchTool");
        model.addAttribute("currentPage", "searchUser");
        response.addObject(model);
        boolean isSafari = userService.isSafari(request);
        response.addObject("isSafari", isSafari);
        List<User> users = userDAO.findAllByCustomTerm(term);
        List<User> shortenedListUsers = new ArrayList<>(10);
        Integer count = 10;
        for(User user : users) {
            if(count != 0){
                count--;
            }
            if(count == 0){
                break;
            }
            shortenedListUsers.add(user);
        }
        response.addObject("users", shortenedListUsers);
        log.debug(users.toString());
        response.addObject("term", term);
        return response;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("users/create");
        User user = userDAO.findUserById(id);
        List<UserRole> userRoles = userRoleDAO.findByUserId(user.getId());
        List<String> userRoleNames = new ArrayList<>();
        if (userRoles.size() > 0) {
            for (UserRole userRole : userRoles) {
                userRoleNames.add(userRole.getRoleName());
            }
        }
        List<Account> account = accountDAO.findAccountsByUserId(user.getId());
        Branch branch = branchDAO.findBranchById(user.getHomeBranch());
        List<Branch> branches = branchDAO.findAll();
        response.addObject("branches", branches);
        response.addObject("form", user);
        response.addObject("homeBranch", branch);
        response.addObject("currentPage", "edit");
        response.addObject("roles", userRoleNames);
        return response;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editSubmit(@PathVariable Integer id, CreateAccountFormBean formBean) {
        ModelAndView response = new ModelAndView("users/create");
        User user = userDAO.findUserById(id);
        if (user == null) {
            user = userService.createUser(formBean);
        }

        userService.populateUserObject(formBean, user);

        userService.assignUserRole(formBean, user);
        log.debug(formBean.toString());
       user = userDAO.save(user);
        if (formBean.getAccountAmount() != null) {
            Account account = new Account();

            account.setAccountAmount(formBean.getAccountAmount());
            account.setAccountType(formBean.getAccountType());
            account.setBranchId(user.getHomeBranch());
            account.setUser(user);
            account.setCreateDate(new Date().toInstant());
            accountDAO.save(account);
        }
response.addObject("currentPage", "edit");
        response.setViewName("redirect:/admin/dashboard");
        return response;
    }

    @GetMapping("/transaction/{id}")
    public ModelAndView transaction(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("users/transactionDetails");
        User user = userDAO.findUserById(authenticatedUserUtils.getCurrentUserObject().getId());
        AccountTransaction accountTransaction = accountTransactionDAO.findAccountTransactionById(id);
        response.addObject("transaction", accountTransaction);
        response.addObject("user", user);
        return response;

    }

    @PreAuthorize("hasAuthority('ADMIN')")

    @GetMapping("/create-account")
    public ModelAndView createAccount() {
        ModelAndView response = new ModelAndView("users/create");
        response.addObject("currentPage", "create");
        List<Branch> branches = branchDAO.findAll();
        response.addObject("branches", branches);
        return response;
    }

    @PostMapping("/create-account")
    public ModelAndView createAccountSubmit( @Valid CreateAccountFormBean form, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();
List<Branch> branches = branchDAO.findAll();
response.addObject("branches", branches);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : {} = {}", ((FieldError) error).getField(), error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            response.addObject("currentPage", "create");
            response.setViewName("users/create");
            return response;
        } else {
            // there were no errors so we can create the new user in the database
            if (form.getRole() == null) {
                form.setRole("USER");
            }
            User user = userService.createUser(form);
            userService.assignUserRole(form, user);
            if (form.getAccountAmount() != null) {
                Account account = new Account();

                account.setAccountAmount(form.getAccountAmount());
                account.setAccountType(form.getAccountType());
                account.setBranchId(user.getHomeBranch());
                account.setUser(user);
                account.setCreateDate(new Date().toInstant());
                accountDAO.save(account);
            }
            //authenticatedUserUtils.manualAuthentication(session, form.getEmail(), form.getPassword());
        }
        response.setViewName("redirect:/admin/dashboard");
        return response;
    }
}