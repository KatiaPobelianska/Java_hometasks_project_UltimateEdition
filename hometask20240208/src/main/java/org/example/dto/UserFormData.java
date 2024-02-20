package org.example.dto;

import java.time.LocalDate;
import java.util.Objects;

public final class UserFormData {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;


    public UserFormData() { }

    public User toUser() {
        return new User(null, firstName, lastName, birthDate, email, null);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserFormData) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.birthDate, that.birthDate) &&
                Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, email);
    }

    @Override
    public String toString() {
        return "UserFormData[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "birthDate=" + birthDate + ", " +
                "email=" + email + ']';
    }

}
