package models;

import play.data.validation.Constraints;

public class User{

    @Constraints.Required
    String firstName;

    @Constraints.Required
    String lastName;

    @Constraints.Required
    @Constraints.Email
    String email;

    @Constraints.Required
    String password;

    public User(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }
}