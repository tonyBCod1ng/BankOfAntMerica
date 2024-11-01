package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    @Query("select u from User u where concat(u.email, '', u.id, '',lower(u.lastName) , lower(u.firstName)) like concat('%', :term, '%') ")
    List<User> findAllByCustomTerm(String term);

    User findByEmailIgnoreCase(String email);
    @Query(value = "select * from users u where u.id = :id", nativeQuery = true)
    User findUserById(Integer id);
}
