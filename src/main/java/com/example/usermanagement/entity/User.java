package com.example.usermanagement.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide a password")
    private String password;

    @Column
    private boolean enabled;

    @Transient
    private String emailAlreadyTaken;

    @Column(nullable = false, unique = true)
    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please provide an email")
    private String email;

    @Column
    private String photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlreadyTaken() {
        return emailAlreadyTaken;
    }

    public void setEmailAlreadyTaken(String emailAlreadyTaken) {
        this.emailAlreadyTaken = emailAlreadyTaken;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
