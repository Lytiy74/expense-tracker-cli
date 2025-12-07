package ua.azaika.expensetrackercli.model;

import java.time.LocalDate;

public record TransactionDTO(
        String description,
        Double amount,
        LocalDate date
) {
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date.toString() +
                '}';
    }
}
