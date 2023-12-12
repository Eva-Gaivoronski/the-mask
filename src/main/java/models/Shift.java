package models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private int id;
    @ManyToMany
    private Role role;
    @ManyToOne
    private Employee employee;
    private String shiftDay; //day of the week scheduled
    private LocalTime shiftStart;
    private LocalTime shiftEnd;
    private Long shiftHours;

    //Constructors
    public Shift () {}


    public Shift(Role role, Employee employee, String ShiftDay, LocalTime shiftStart, LocalTime shiftEnd) { // ?????
        this.role = role;
        this.employee = employee;
        this.shiftDay = ShiftDay;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    shiftHours = ChronoUnit.HOURS.between(shiftStart, shiftEnd);

    //Hash mapping - need help

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shift shift)) return false;
        return id == shift.id && Objects.equals(role, shift.role) && Objects.equals(employee, shift.employee) && Objects.equals(shiftDay, shift.shiftDay) && Objects.equals(shiftStart, shift.shiftStart) && Objects.equals(shiftEnd, shift.shiftEnd) && Objects.equals(shiftHours, shift.shiftHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, employee, shiftDay, shiftStart, shiftEnd, shiftHours);
    }


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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getShiftDay() {
        return shiftDay;
    }

    public void setShiftDay(String shiftDay) {
        shiftDay = shiftDay;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(LocalTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public Long getShiftHours() {
        return shiftHours;
    }

    public void setShiftHours(Long shiftHours) {
        this.shiftHours = shiftHours;
    }
}
