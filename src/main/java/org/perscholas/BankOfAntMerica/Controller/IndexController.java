package org.perscholas.BankOfAntMerica.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
class IndexController {
    @GetMapping("/login")
    ModelAndView index() {
        return new ModelAndView("auth/login");
    }
}
