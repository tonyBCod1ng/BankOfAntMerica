package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

}
