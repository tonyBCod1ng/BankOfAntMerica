package org.perscholas.BankOfAntMerica.Security;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
import org.perscholas.BankOfAntMerica.database.DAO.UserRoleDAO;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.perscholas.BankOfAntMerica.database.Entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    private UserDAO userDAO;

    public static Collection<? extends GrantedAuthority> buildGrantedAuthorities(Collection<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmailIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        //Account status check
        boolean enabled = true;
        boolean accountActive = true;
        boolean credentialsCurrent = true;
        boolean accountUnlocked = true;

        Collection<UserRole> userRoles = userRoleDAO.findByUserId(user.getId());
        Collection<? extends GrantedAuthority> authorities = buildGrantedAuthorities(userRoles);

       /*  this User object is part of Spring Security because both objets are named User,
       we have to use the full path to the object*/

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(

                user.getEmail(),  // this parameter is the username, in our case the user from the database

                user.getPassword(), // this is the users encrypted password from the database

                enabled, // is this account enabled, if false, then spring security will deny access

                accountActive,
                credentialsCurrent,
                accountUnlocked,
                authorities);// this is the list of security roles that the user has

        return userDetails;
    }
}
