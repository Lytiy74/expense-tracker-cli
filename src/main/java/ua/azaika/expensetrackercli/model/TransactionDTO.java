package ua.azaika.expensetrackercli.model;

import java.time.LocalDate;

public record TransactionDTO(
        Integer id,
        String description,
        Double amount,
        LocalDate date
) {
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
