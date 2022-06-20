package com.example.realestateregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {
    @NotBlank(message = "firstname is mandatory")
    String firstname;
    @NotBlank(message = "surname is mandatory")
    String surname;
    @NotBlank(message = "address is mandatory")
    String address;
    @Pattern(regexp = "^[\\d]{4,20}$", message = "only type numbers")
    @Column(name = "phone_number")
    String phoneNumber;
}
