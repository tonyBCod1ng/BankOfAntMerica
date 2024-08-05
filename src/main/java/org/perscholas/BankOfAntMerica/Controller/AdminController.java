package org.perscholas.BankOfAntMerica.Controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.DAO.AccountDAO;
import org.perscholas.BankOfAntMerica.database.DAO.AccountTransactionDAO;
import org.perscholas.BankOfAntMerica.database.DAO.BranchDAO;
import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.perscholas.BankOfAntMerica.database.Entity.AccountTransaction;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {


        ModelAndView response = new ModelAndView("admin/dashboard");
        User user = authenticatedUserUtils.getCurrentUserObject();
        response.addObject("user", user);
        Branch branch = branchDAO.findByManagerId(user.getId());
        response.addObject("branch", branch);

        List<AccountTransaction> accountTransactions = accountTransactionDAO.findByBranchId(branch.getId());
        response.addObject("accountTransactions", accountTransactions);

        List<Account> managedAccounts = accountDAO.findByBranchId(branch.getId());

        return response;
    }

    @GetMapping("/searchTool")
    public ModelAndView searchTool(@RequestParam(required = false) String term) {
        ModelAndView response = new ModelAndView("admin/searchTool");
        if (term != null) {
            List<Account> searchedAccount = accountDAO.findAllByCustomerTerm(term);
            response.addObject("foundAccounts", searchedAccount);
            response.addObject(term);
        }
        return response;
    }
}
