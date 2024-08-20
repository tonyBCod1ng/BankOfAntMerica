package org.perscholas.BankOfAntMerica.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 7)
    @NotNull
    @Column(name = "account_type", nullable = false, length = 7)
    private String accountType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "account_amount", nullable = false, columnDefinition = "DECIMAL", precision = 2)
    private Double accountAmount;

    @Column(name = "last_edited")
    private Instant lastEdited;

    @Column(name = "create_date")
    private Instant createDate;

    @NotNull
    @Column(name = "branch_id", nullable = false)
    private Integer branchId;


}