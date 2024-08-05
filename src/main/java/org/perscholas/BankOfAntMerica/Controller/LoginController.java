package org.perscholas.BankOfAntMerica.Controller;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.DAO.BranchDAO;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Getter
@Setter
@ToString
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;
    @Autowired
    private BranchDAO branchDAO;


}
