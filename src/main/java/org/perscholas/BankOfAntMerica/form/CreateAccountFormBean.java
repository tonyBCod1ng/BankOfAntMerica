package org.perscholas.BankOfAntMerica.form;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    @Email(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Please enter valid Email")
    private String email;
    private String username;
    @NotEmpty
    private String password;

    private String role;

    private Integer branch;

    private Instant createDate;

    private String accountType;

    private Integer accountAmount;

    private Instant lastEdited;

}
