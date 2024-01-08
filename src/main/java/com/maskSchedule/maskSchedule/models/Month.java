package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Month {
    @Id
    @GeneratedValue
    private int id;
    private int month;
    private String name;
    List<Week> week = new ArrayList<>();

    public Month () {}

    public Month (int month, String name) {
        this.month = month;
        this.name = name;
    }

    //hash mapping

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return id == month.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // getters and setters


    public int getId() {
        return id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Week> getWeekList() {
        return week;
    }

    public void addWeek(List<Week> week) {
        week.add(new Week());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
