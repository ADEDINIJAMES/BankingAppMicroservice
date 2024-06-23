package com.tumtech.apigateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tumtech.apigateway.enums.Roles;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
@Getter
@Setter
public class Users {
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    private String phone;
    private LocalDate dob;
    private String password;
    private String confirmPassword;
    private String gender;

    private Roles userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Roles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Roles userRoles) {
        this.userRoles = userRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Users(String firstName, String email, Roles userRoles, String lastName, String phone, LocalDate dob, String password, String confirmPassword, String gender) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.phone = phone;
        this.dob = dob;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
        this.userRoles= userRoles;
    }
}
