package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Day {
    @Id
    @GeneratedValue
    private int id;

    private int day;

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
}
