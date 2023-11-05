package com.clawson.restful.model;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Manager {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;
    private String name;
    private @JsonIgnore String password;
    private String[] roles;
    
    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    protected Manager() {}

    /**
     * @param name
     * @param password
     * @param roles
     */
    public Manager(String name, String password, String... roles) {
        this.name = name;
        this.setPassword(password);
        this.roles = roles;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the roles
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(getId(), manager.getId()) && Objects.equals(getName(), manager.getName()) && Objects.equals(getPassword(), manager.getPassword()) && Arrays.equals(getRoles(), manager.getRoles());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getPassword());
        result = 31 * result + Arrays.hashCode(getRoles());
        return result;
    }

    @Override
    public String toString() {
        return "Manager [id=" + id + ", name=" + name + ", password=" + password + ", roles=" + Arrays.toString(roles)
                + "]";
    }

    
    
}
