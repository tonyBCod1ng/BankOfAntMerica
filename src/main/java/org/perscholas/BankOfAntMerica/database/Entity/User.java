package org.perscholas.BankOfAntMerica.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Size(max = 45)
    @Column(name = "address_line_1", length = 45)
    private String addressLine1;

    @Size(max = 45)
    @Column(name = "address_line_2", length = 45)
    private String addressLine2;

    @Size(max = 45)
    @Column(name = "city", length = 45)
    private String city;

    @Size(max = 45)
    @Column(name = "state", length = 45)
    private String state;

    @Size(max = 45)
    @Column(name = "zipcode", length = 45)
    private String zipcode;

    @Size(max = 45)
    @Column(name = "phone", length = 45)
    private String phone;

    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    @NotNull
    @Column(name = "home_branch", nullable = false)
    private Integer homeBranch;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}