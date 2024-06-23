package com.zlagoda.dto;

import com.zlagoda.entity.Employee;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    public enum Role {
        CASHIER,
        MANAGER
    }

    private String id;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 1, max = 50, message = "Surname must be between 1 and 50 characters")
    private String surname;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    // @NotNull(message = "Password cannot be null")
    // @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    // private String password;

    @Nullable
    @Size(max = 50, message = "Patronymic must be maximum 50 characters")
    private String patronymic;

    @NotNull(message = "Role cannot be null")
    private Employee.Role role;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    @Digits(integer = 13, fraction = 2, message = "Salary must have at most 10 integer digits and 4 decimal places")
    private BigDecimal salary;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    @NotNull(message = "Start date cannot be null")
    @PastOrPresent(message = "Start date must be in the past or present")
    private Date startDate;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 1, max = 13, message = "Phone number must be between 1 and 13 characters")
    private String phoneNumber;

    @NotNull(message = "City cannot be null")
    @Size(min = 1, max = 50, message = "City must be between 1 and 50 characters")
    private String city;

    @NotNull(message = "Street cannot be null")
    @Size(min = 1, max = 50, message = "Street must be between 1 and 50 characters")
    private String street;

    @NotNull(message = "Zip code cannot be null")
    @Size(min = 1, max = 9, message = "Zip code must be between 1 and 9 characters")
    private String zipCode;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    public void normalize() {
        if (id != null)
            id = id.trim();
        if (surname != null)
            surname = surname.trim();
        if (name != null)
            name = name.trim();
        if (patronymic != null)
            patronymic = patronymic.trim();
        if (phoneNumber != null)
            phoneNumber = phoneNumber.trim();
        if (city != null)
            city = city.trim();
        if (street != null)
            street = street.trim();
        if (zipCode != null)
            zipCode = zipCode.trim();
    }
}
