package org.perscholas.BankOfAntMerica.database.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Size(max = 45)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 45)
    private String roleName;

    @Column(name = "create_time")
    private Instant createTime;

}