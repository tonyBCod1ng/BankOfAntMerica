package org.perscholas.BankOfAntMerica.Controller;

import jakarta.servlet.http.HttpServletRequest;
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
import org.perscholas.BankOfAntMerica.form.CreateTransferBean;
import org.perscholas.BankOfAntMerica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/")
class IndexController {
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private AccountTransactionDAO accountTransactionDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    UserService userService;

    @GetMapping("/")
    ModelAndView index(HttpServletRequest request, Model model){
        ModelAndView response = new ModelAndView("index");
        model.addAttribute("currentPage", "transfer");
        response.addObject(model);
        String url = request.getRequestURI();

        User user = authenticatedUserUtils.getCurrentUserObject();
        response.addObject("user", user);
        List<Account> accounts = accountDAO.findAccountsByUserId(user.getId());
        response.addObject("accounts", accounts);
        return response;
    }
    @PostMapping("/")
    ModelAndView indexPost(@Valid CreateTransferBean formBean, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView response = new ModelAndView("transfer");

        User currentUser = authenticatedUserUtils.getCurrentUserObject();
        List<Account> accounts = accountDAO.findAccountsByUserId(currentUser.getId());
        response.addObject("accounts", accounts);
        log.debug(formBean.toString());
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : {} = {}", ((FieldError) error).getField(), error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", formBean);
            response.addObject("currentPage", "transfer");
            response.setViewName("index");
            return response;
        } else {
            Account sender = accountDAO.findAccountById(formBean.getSender());
            Double senderBalance = sender.getAccountAmount();
            Account receiver = accountDAO.findAccountById(formBean.getReceiver());
            Double receiverBalance = receiver.getAccountAmount();
            Double amount = formBean.getTransferAmount();
            AccountTransaction senderTransaction = new AccountTransaction();
            AccountTransaction receiverTransaction = new AccountTransaction();

            //set up senders transaction
            if (sender.getId() != receiver.getId()) {
                if (senderBalance > amount) {
                    sender.setAccountAmount((senderBalance - amount));
                    accountDAO.save(sender);
                    senderTransaction.setAccountId(sender.getId());
                    senderTransaction.setAmount(-amount);
                    senderTransaction.setBranchId(currentUser.getHomeBranch());
                    senderTransaction.setCreateDate(new Date().toInstant());
                    senderTransaction.setLastChanged(new Date().toInstant());
                }
                //set up receivers transaction
                receiver.setAccountAmount(receiverBalance + amount);
                accountDAO.save(receiver);
                receiverTransaction.setAccountId(receiver.getId());
                receiverTransaction.setAmount(amount);
                receiverTransaction.setBranchId(currentUser.getHomeBranch());
                receiverTransaction.setCreateDate(new Date().toInstant());
                receiverTransaction.setLastChanged(new Date().toInstant());

              senderTransaction =  accountTransactionDAO.save(senderTransaction);
               receiverTransaction = accountTransactionDAO.save(receiverTransaction);
               response.addObject("senderTransaction", senderTransaction);
               response.addObject("receiverTransaction", receiverTransaction);
            }
            response.addObject("form", formBean);
            response.addObject("currentPage", "transfer");
            response.setViewName("index");
            return response;
        }
    }
}
