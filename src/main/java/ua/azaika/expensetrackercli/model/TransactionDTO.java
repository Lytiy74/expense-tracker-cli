package ua.azaika.expensetrackercli.model;

import java.time.LocalDateTime;

public record TransactionDTO(
        String description,
        Double amount,
        LocalDateTime timestamp
) {
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp.toLocalDate() +
                '}';
    }
}
