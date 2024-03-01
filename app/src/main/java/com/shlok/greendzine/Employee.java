package com.shlok.greendzine;

public class Employee {
    private int id;
    private String name;
    private String dob;
    private String role;

    public Employee(int id, String name, String dob, String role) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.role = role;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getRole() {
        return role;
    }

    // Setter methods (if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

