package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Month {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private int month;
    private String name;
    @ManyToOne
    @JoinColumn(name = "year")
    private Year year;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "month")
    private final List<Week> weeks = new ArrayList<>();

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

    public List<Week> getWeeks() {
        return weeks;
    }

    public Week getWeek(int weekInt) {
        return weeks.get(weekInt);
    }

    public void addWeek(int week) {
        weeks.add(new Week(week));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
