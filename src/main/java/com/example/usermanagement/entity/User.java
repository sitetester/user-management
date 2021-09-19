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

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

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

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmailAlreadyTaken() {
        return emailAlreadyTaken;
    }

    public User setEmailAlreadyTaken(String emailAlreadyTaken) {
        this.emailAlreadyTaken = emailAlreadyTaken;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public User setRoles(Collection<Role> roles) {
        this.roles = roles;
        return this;
    }
}
