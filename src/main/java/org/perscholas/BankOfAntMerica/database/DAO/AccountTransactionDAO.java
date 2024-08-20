package org.perscholas.BankOfAntMerica.database.DAO;

import org.perscholas.BankOfAntMerica.database.Entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountTransactionDAO extends JpaRepository<AccountTransaction, Double> {
    List<AccountTransaction>findByBranchId(Integer id);
    List<AccountTransaction>findByAccountId(Integer id);
    AccountTransaction findAccountTransactionById(Integer id);

}
