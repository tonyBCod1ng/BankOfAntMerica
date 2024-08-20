package org.perscholas.BankOfAntMerica.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.perscholas.BankOfAntMerica.database.Entity.Account;
import org.perscholas.BankOfAntMerica.database.Entity.Branch;
@Getter
@Setter
@ToString
public class CreateTransferBean {
    @NotNull(message = "Select the Sending Account")
    Integer sender;
    @NotNull(message = "Select the Receiving Account")
    Integer receiver;
    @NotNull(message = "Select Transfer Amount ")
    Double transferAmount;
}
