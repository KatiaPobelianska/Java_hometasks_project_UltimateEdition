package org.example.dto;

import java.time.LocalDate;

public record User(Long id, String firstName, String lastName, LocalDate birthDate, String email, String status) {
    private static long count = 0;

    public User(User original) {
        this(count++, original.firstName, original.lastName, original.birthDate, original.email, original.status);
    }

}
