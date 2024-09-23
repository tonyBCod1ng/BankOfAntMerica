package org.perscholas.BankOfAntMerica.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.Security.AuthenticatedUserUtils;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.util.Collections;

@Slf4j
//@Bean
//public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//    dataSource.setUrl("jdbc:mysql://mysql:3306/bank_of_antmerica?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
//    dataSource.setUsername("root");
//    dataSource.setPassword("my-secret-pw");
//
//    RetryTemplate retryTemplate = new RetryTemplate();
//    retryTemplate.setRetryPolicy(new SimpleRetryPolicy(30, Collections.singletonMap(Exception.class, true)));
//    retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
//
//    return retryTemplate.execute(context -> {
//        dataSource.getConnection();
//        return dataSource;
//    });
//}
@Component
public class YourInteceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticatedUserUtils authenticatedUserUtils;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

        User currentUser = null;
        if (request.getUserPrincipal() != null) {
            currentUser = authenticatedUserUtils.getCurrentUserObject();
            request.setAttribute("currentUser", currentUser);
        log.debug("Current user: {}" , currentUser);
        }

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
