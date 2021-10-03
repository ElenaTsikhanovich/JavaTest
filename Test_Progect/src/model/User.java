package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private ERole[] role;
    private String email;
    private List<String> number;

    public User(){

    }

    public User(String firstName, String lastName, ERole[] role, String email, List<String> number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.number = number;
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

    public ERole[] getRole() {
        return role;
    }

    public void setRole(ERole[] role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getNumber() {
        return number;
    }

    public void setNumber(List<String> number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                '}';
    }
}
