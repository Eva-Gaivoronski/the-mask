package models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Shift {

    @Id
    @GeneratedValue
    private int id;
    private Role role;
    private User user;
    private String ShiftDay;



}
