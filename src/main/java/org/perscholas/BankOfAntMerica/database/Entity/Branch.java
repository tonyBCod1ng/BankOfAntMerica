package org.perscholas.BankOfAntMerica.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Size(max = 45)
    @NotNull
    @Column(name = "state", nullable = false, length = 45)
    private String state;

    @Size(max = 45)
    @NotNull
    @Column(name = "zipcode", nullable = false, length = 45)
    private String zipcode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

}