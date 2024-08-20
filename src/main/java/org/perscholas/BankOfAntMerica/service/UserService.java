package org.perscholas.BankOfAntMerica.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.*;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.DAO.UserRoleDAO;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.database.Entity.UserRole;
import org.perscholas.BankOfAntMerica.form.CreateAccountFormBean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import java.util.*;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateAccountFormBean form) {
        String password = passwordEncoder.encode(form.getPassword());
        // there were no errors so we can create the new user in the database
        User user = new User();
        user.setCreateTime(new Date().toInstant());
        populateUserObject(form, user);
        user.setPassword(password);

        // save the user to the database
        userDAO.save(user);

        return user;
    }

    public void populateUserObject(CreateAccountFormBean form, User user) {
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setAddressLine1(form.getAddressLine1());
        user.setAddressLine2(form.getAddressLine2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZipcode(form.getZipcode());
        user.setPhone(form.getPhone());
        user.setFirstName(form.getFirstName());
        user.setEmail(form.getEmail());
        user.setHomeBranch(form.getBranch());
        // we are getting in a plain text password because the user entered it into the form
    }

    public UserRole assignUserRole(CreateAccountFormBean form, User user) {

        if(form.getRole() == null){
            form.setRole("USER");
        }
        String role = form.getRole();
            UserRole assignedUserRole = new UserRole();
                assignedUserRole.setRoleName(role);
        if(user != null) {
            List<UserRole> usersRole = userRoleDAO.findByUserId(user.getId());
            if(usersRole.isEmpty()) {
                assignedUserRole.setUserId(user.getId());
                assignedUserRole.setCreateTime(new Date().toInstant());
                userRoleDAO.save(assignedUserRole);
            }
            for(UserRole userRole : usersRole) {
                if(userRole.getRoleName().equals(role)) {
                    break;
                }
                userRole.setRoleName(role);
            }


        }
            return assignedUserRole;

    }
    public boolean isSafari(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        //log.debug(userAgent);
        if(userAgent.contains("Safari/605.1.15")){
            if(!userAgent.contains("Ddg/17.5")){
                if(!userAgent.contains("537.36")){
                    log.debug(userAgent);
                    return true;
                }
            }
        }
        return false;
    }
}