package org.perscholas.BankOfAntMerica.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class YourInteceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    User currentUser = authenticatedUserUtils.getCurrentUserObject();
    if (currentUser != null) {
        request.setAttribute("currentUser", currentUser);
        log.info("Current user: {}" , currentUser);
        return true;
    }
    return false;
}
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        boolean isSafari = isSafari(request);
        modelAndView.addObject("isSafari", isSafari);
        log.debug("isSafari:{}", isSafari);


    }
    private boolean isSafari(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        log.debug(userAgent);
        if(userAgent.contains("Safari/605.1.15")){
            if(!userAgent.contains("Ddg/")){
                if(!userAgent.contains("537.36")){
                    log.debug(userAgent);
                    return true;
                }
            }
        }
        return false;
    }
}
