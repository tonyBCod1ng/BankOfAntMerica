package org.perscholas.BankOfAntMerica.Controller;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.DAO.AccountTransactionDAO;
import org.perscholas.BankOfAntMerica.database.DAO.BranchDAO;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Getter
//@Setter
//@ToString
@Slf4j
@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;
    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private AccountTransactionDAO accountTransactionDAO;

    @GetMapping("/login")
    ModelAndView index(@RequestParam(required = false) String error) {
        ModelAndView response = new ModelAndView("auth/login");
        return response;
    }
    @PostMapping("/login")
    ModelAndView login() {
        ModelAndView response = null;
        User user = authenticatedUserUtils.getCurrentUserObject();

        response.addObject("user", user);
        return response;    }

    @PostMapping("/success")
    ModelAndView loginSuccess() {
        ModelAndView response = new ModelAndView();
        User user = authenticatedUserUtils.getCurrentUserObject();
        if(authenticatedUserUtils.isUserInRole("ADMIN")){
            response = new ModelAndView("redirect:http://localhost:8080/admin/dashboard");

        }
        if(!authenticatedUserUtils.isUserInRole("ADMIN")){
            response.setViewName("redirect:http://localhost:8080/users/dashboard");
        }
        response.addObject("user", user);
        return response;
    }


}
