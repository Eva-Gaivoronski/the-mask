package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private int id;
    private Role role;
    private Account account;
    private Employee employee;
    private String ShiftDay;

    //Constructors

    public Shift () {}

    public Shift(Role role, Account account, Employee employee, String ShiftDay) {

    }

    //Hash mapping - need help

    //Getters and Setters

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getShiftDay() {
        return ShiftDay;
    }

    public void setShiftDay(String shiftDay) {
        ShiftDay = shiftDay;
    }
}
