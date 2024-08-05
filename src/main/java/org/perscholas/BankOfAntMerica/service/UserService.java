package org.perscholas.BankOfAntMerica.service;

import lombok.extern.slf4j.*;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.Entity.User;
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
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateAccountFormBean form) {
        // there were no errors so we can create the new user in the database
        User user = new User();
        String password = passwordEncoder.encode(form.getPassword());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setAddressLine1(form.getAddressLine1());
        user.setAddressLine2(form.getAddressLine2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZipcode(form.getZipcode());
        user.setPhone(form.getPhone());
        user.setFirstName(form.getFirstName());
        user.setEmail(form.getUsername());
        // we are getting in a plain text password because the user entered it into the form
        user.setPassword(password);
        user.setCreateDate(new Date());


        // save the user to the database
        userDAO.save(user);

        return user;
    }

}