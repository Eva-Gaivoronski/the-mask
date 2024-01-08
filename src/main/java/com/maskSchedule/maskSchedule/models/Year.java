package com.maskSchedule.maskSchedule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
public class Year {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private int year;
    @OneToMany(mappedBy = "year")
    List<Month> months = new ArrayList<>();

    // building the year
    private void build() {
        // create calendar instance
        Calendar cal = new GregorianCalendar();

        // create arraylist of months
        ArrayList<String> monthNames = new ArrayList<>(12);
        monthNames.add("January");
        monthNames.add("February");
        monthNames.add("March");
        monthNames.add("April");
        monthNames.add("May");
        monthNames.add("June");
        monthNames.add("July");
        monthNames.add("August");
        monthNames.add("September");
        monthNames.add("October");
        monthNames.add("November");
        monthNames.add("December");

        // loop this month arraylist
        for (int monthInt = 0; monthInt < monthNames.size(); monthInt++){
            int day = 1;

            //set calendar date to the beginning of each month
            cal.set(year,monthInt, 1);

            //get the day the week starts on and number of weeks
            int startDay = cal.getFirstDayOfWeek();
            int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int numberOfWeeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

            //add month to month list
            months.add(new Month(monthInt,monthNames.get(monthInt)));
            for (int weekInt = 0; weekInt < numberOfWeeks; weekInt++) {

                //add weeks to month
                months.get(monthInt).addWeek(new Week(weekInt));

                //check first week to set start day of the month
                if (weekInt == 0) {

                    //add empty days until the start of the month for the first week
                    for (int blankDay = 0; blankDay < startDay; blankDay++) {
                        months.get(monthInt).getWeek(weekInt).addBlankDay();
                    }

                    for (int daysOfFirstWeek = startDay; daysOfFirstWeek < 7; daysOfFirstWeek++) {

                        //start counting days of the month
                        months.get(monthInt).getWeek(weekInt).addDay(day);
                        day++;

                    }
                }

                    // prevent mount from exceeding max days
                if (weekInt != 0) {
                    for (int daysOfWeek = 0; daysOfWeek < 7; daysOfWeek++) {
                        if (day <= numberOfDays) {
                            months.get(monthInt).getWeek(weekInt).addDay(day);
                            day++;

                        }
                    }
                }

            }
        }
    }



    public Year(int year) {
        this.year = year;
        build();
    }


    //hash mapping

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return id == year.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    // getters and setters


    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Month> getMonths() {
        return months;
    }

    public Month getMonth(int monthInt){
        return months.get(monthInt);
    }

}
