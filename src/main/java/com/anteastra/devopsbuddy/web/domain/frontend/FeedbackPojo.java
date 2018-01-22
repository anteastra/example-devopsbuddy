package com.anteastra.devopsbuddy.web.domain.frontend;

import java.io.Serializable;

public class FeedbackPojo implements Serializable {
    private static final long serialVersionUID = 1l;

    private String email;
    private String firstName;
    private String lastName;
    private String feedback;

    @Override
    public String toString() {
        return "FeedbackPojo{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", feedBack='" + feedback + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
