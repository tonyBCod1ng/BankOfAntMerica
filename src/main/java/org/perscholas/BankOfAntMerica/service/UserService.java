package org.perscholas.BankOfAntMerica.service;

import lombok.extern.slf4j.*;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.form.CreateAccountFormBean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public User createUser(CreateAccountFormBean form) {
        // there were no errors so we can create the new user in the database
        User user = new User();

        user.setEmail(form.getEmail());

        // we are getting in a plain text password because the user entered it into the form
        user.setPassword(form.getPassword());
        user.setCreateDate(new Date());


        // save the user to the database
        userDAO.save(user);

        return user;
    }

}