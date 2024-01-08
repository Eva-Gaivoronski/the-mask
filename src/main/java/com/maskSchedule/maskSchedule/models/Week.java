package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Week {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private int week;
    @ManyToOne
    @JoinColumn(name="month_id")
    private Month month;

    @OneToMany(mappedBy = "week_id")
    List<Day> days = new ArrayList<>();

    public Week () {}

    public Week (int week) {
        this.week = week;
    }

    // hash mapping

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Week week = (Week) o;
        return id == week.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // getters and setters


    public int getId() {
        return id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getDay(int dayInt){
        return days.get(dayInt);
    }

    public void addDay(Day day) {
        days.add(new Day());
    }
}
