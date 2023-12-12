package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Employee employee;

    @NotNull
    private boolean isAdmin;

    @ManyToMany
    private List<Shift> shifts = new ArrayList<>();

    //Constructor
    public Account() {}

    public Account(Employee employee, boolean isAdmin, List<Shift> someShifts) {
        this.employee = employee;
        this.isAdmin = isAdmin;
        this.shifts = someShifts;

    }

    //Getters and setters
    public int getId() { return id; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public List<Shift> getShifts() { return shifts; }
    public void setShifts(List<Shift> shifts) { this.shifts = shifts; }

    public Employee getEmployee() { return employee;}
    public void setEmployee(Employee employee) { this.employee = employee; }

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
