package org.example.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public final class ParticipantFormData {
   @NotBlank
   @Size(min=1,max=100,message = "Name should be not longer than 100 and not shorter than 1 symbol")
   private String firstName;
    @NotBlank
    @Size(min=1,max=100,message = "Last name should be not longer than 100 and not shorter than 1 symbol")
    private String lastName;
    @Min(value=18,message = "You must be adult (18+)")
    private int age;
    @AssertTrue(message="You must give your consent for collecting your personal data")
    private boolean isAgree;

    public ParticipantFormData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean getIsAgree() {
        return isAgree;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setConsent(boolean isAgree) {
        this.isAgree = isAgree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantFormData that = (ParticipantFormData) o;
        return age == that.age && isAgree == that.isAgree && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, isAgree);
    }
}
