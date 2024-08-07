package org.perscholas.BankOfAntMerica.Controller;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

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
    public ModelAndView dashboard() {


        ModelAndView response = new ModelAndView("admin/dashboard");
        User user = authenticatedUserUtils.getCurrentUserObject();
        response.addObject("user", user);
        Branch branch = branchDAO.findBranchById(user.getHomeBranch());


        List<AccountTransaction> accountTransactions = accountTransactionDAO.findByBranchId(branch.getId());
        response.addObject("accountTransactions", accountTransactions);

        List<Account> managedAccounts = accountDAO.findByBranchId(branch.getId());
        response.addObject("managedAccounts", managedAccounts);

        return response;
    }

    @GetMapping("/searchTool")
    public ModelAndView searchTool(@RequestParam(required = false) String term) {
        ModelAndView response = new ModelAndView("admin/searchTool");
        if (term != null) {
            List<Account> searchedAccount = accountDAO.findAllByCustomerTerm(term);
            response.addObject("foundAccounts", searchedAccount);
            response.addObject("term", term);
        }
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
        Account account = new Account();

            account.setAccountAmount(formBean.getAccountAmount());
            account.setAccountType(formBean.getAccountType());
            account.setBranchId(user.getHomeBranch());
            account.setUserId(user.getId());
            account.setCreateDate(new Date().toInstant());
            accountDAO.save(account);

        userService.populateUserObject(formBean, user);
        userService.assignUserRole(formBean);
        log.debug(formBean.toString());
        userDAO.save(user);

        response.setViewName("redirect:/");
        return response;
    }
}
