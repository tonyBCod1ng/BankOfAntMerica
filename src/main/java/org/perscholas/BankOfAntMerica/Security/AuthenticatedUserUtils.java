package org.perscholas.BankOfAntMerica.Security;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class AuthenticatedUserUtils {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    AuthenticationManager authenticationManager;

    public String getCurrentUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            if (context.getAuthentication().getPrincipal() instanceof AnonymousAuthenticationToken) {
                return null;
            }
            final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
            return principal.getUsername();
        } else {
            return null;
        }
    }

    public User getCurrentUserObject() {
        String username = getCurrentUsername();
        User user;
        if (username == null) {
            return null;
        }
        user = userDAO.findByEmailIgnoreCase(username);
        return user;
    }
    public void manualAuthentication(HttpSession session, String username, String unencryptedPassword) {
        // reset security principal to be the new user information
        Authentication request = new UsernamePasswordAuthenticationToken(username, unencryptedPassword);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(result);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    public boolean isUserInRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(role)) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        return (authentication != null && authentication.isAuthenticated());
    }
}
