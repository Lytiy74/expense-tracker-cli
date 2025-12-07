package ua.azaika.expensetrackercli.model;

import java.time.LocalDateTime;

public record Transaction(
        String description,
        Double amount,
        LocalDateTime timestamp
) {
}
