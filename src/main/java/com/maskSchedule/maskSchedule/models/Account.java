package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private boolean isAdmin;

    @ManyToMany
    private List<Shift> shifts = new ArrayList<>();

    @OneToMany
    private List<Role> roles = new ArrayList<>();

    //Constructor
    public Account() {}

    public Account(boolean isAdmin, List<Shift> someShifts, List<Role> someRoles) {
        this.isAdmin = isAdmin;
        this.shifts = someShifts;
        this.roles = someRoles;
    }

    //Getters and setters
    public int getId() { return id; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public List<Shift> getShifts() { return shifts; }
    public void setShifts(List<Shift> shifts) { this.shifts = shifts; }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }

    //hashCode and equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
