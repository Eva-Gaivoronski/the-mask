package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Day {
    @Id
    @GeneratedValue
    private int id;
    private int day;
    @ManyToOne
    @JoinColumn(name = "week")
    private Week week;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "days")
    private List<Employee> employees = new ArrayList<>();



    public Day () {}

    public Day (int day) {
        this.day = day;
    }

    // hash mapping

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return id == day.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        if (day == 0){
            return "";
        }
        return ""+day;//check value for day to print a blank

    }

    // getters and setters

    public int getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees ;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Week getWeek() {
        return week;
    }
}
