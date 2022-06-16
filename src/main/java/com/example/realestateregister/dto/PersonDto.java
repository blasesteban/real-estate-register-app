package com.example.realestateregister.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class PersonDto {
    @NotBlank(message = "firstname is mandatory")
    String firstname;
    @NotBlank(message = "surname is mandatory")
    String surname;
    @NotBlank(message = "address is mandatory")
    String address;
    @Pattern(regexp = "^[0-9]{4,10}$", message = "only type numbers")
    @Column(name = "phone_number")
    String phoneNumber;
}
