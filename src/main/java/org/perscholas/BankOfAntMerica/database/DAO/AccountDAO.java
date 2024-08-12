package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Integer> {

    @Query("select a from Account a where concat(a.id, '', a.branchId, '', a.user.id) like concat('%', :term, '%') ")
    List<Account>findAllByCustomerTerm(String term);
    Account findAccountByUserId(int userId);
    List<Account> findAccountsByUserId(int userId);

    //@Query(value = "select a from accounts a, user u where a.user_id = u.id and u.home_branch = :branchId", nativeQuery = true)
    List<Account> findByBranchId(int branchId);
    Account findAccountById(int id);
}
