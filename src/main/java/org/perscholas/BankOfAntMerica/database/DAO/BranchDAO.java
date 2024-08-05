package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchDAO extends JpaRepository<Branch, Integer> {
    Branch findByManagerId(Integer managerId);
    Branch findById(int id);
}
