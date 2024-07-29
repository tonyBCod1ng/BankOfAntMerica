package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUserId(int userId);
}
