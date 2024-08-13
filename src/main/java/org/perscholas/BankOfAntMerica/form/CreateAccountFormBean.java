package org.perscholas.BankOfAntMerica.form;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@ToString
public class CreateAccountFormBean {

    private Integer id;
   @Pattern(regexp = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$", message = "What is your REAL first name?")
    @NotEmpty(message = "Please add your first name")
    private String firstName;
    @Pattern(regexp = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$", message = "What is your REAL last name?")
    @NotEmpty(message = "Please add your last name")
    private String lastName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zipcode;

    private String phone;

    @Email(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Please enter valid Email")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\s:])(\\S){8,16}$",
            message = "password must contain 1 number (0-9)</br>" +
            "password must contain 1 uppercase letters </br>" +
            "password must contain 1 lowercase letters</br>" +
            "password must contain 1 non-alpha numeric number</br>" +
            "password is 8-16 characters with no space")
    @NotEmpty(message = "Password cannot be left blank")
    private String password;

    private String role;

    private Integer branch;

    private Instant createDate;

    private String accountType;

    private Integer accountAmount;

    private Instant lastEdited;

}
