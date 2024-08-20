package org.perscholas.BankOfAntMerica.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "account_transactions")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @NotNull
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL", precision = 2)
    private Double amount;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "last_changed")
    private Instant lastChanged;

    @NotNull
    @Column(name = "branch_id", nullable = false)
    private Integer branchId;

}