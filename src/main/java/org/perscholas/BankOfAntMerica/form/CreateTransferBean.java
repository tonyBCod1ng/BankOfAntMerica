package org.perscholas.BankOfAntMerica.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
@Getter
@Setter
@ToString
public class CreateTransferBean {
    Integer sender;
    Integer receiver;
    Integer transferAmount;
}
