package com.example.kissanconnect;

public class Scheme {
    private String name;
    private String ministry;
    private String description;
    private String benefit;
    private String deadline;

    // Constructor to initialize the scheme data
    public Scheme(String name, String ministry, String description, String benefit, String deadline) {
        this.name = name;
        this.ministry = ministry;
        this.description = description;
        this.benefit = benefit;
        this.deadline = deadline;
    }

    // Getters
    public String getName() { return name; }
    public String getMinistry() { return ministry; }
    public String getDescription() { return description; }
    public String getBenefit() { return benefit; }
    public String getDeadline() { return deadline; }
}
