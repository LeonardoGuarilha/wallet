package com.wallet.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @Length(min = 3, max = 50, message = "Name must have between 3 and 50 characters")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Length(min = 6, message = "Password mus have at least 6 characters")
    private String password;
}
