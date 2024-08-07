package org.perscholas.BankOfAntMerica.form;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@ToString
public class CreateAccountFormBean {

    private Integer id;

    private String firstName;

    private String lastName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zipcode;

    private String phone;

    private String username;

    private String password;

    private String role;

    private Integer branch;

    private Instant createDate;

    private String accountType;

    private Integer accountAmount;

    private Instant lastEdited;

}
