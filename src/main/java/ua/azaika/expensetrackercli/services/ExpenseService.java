package ua.azaika.expensetrackercli.services;

import org.springframework.stereotype.Service;
import ua.azaika.expensetrackercli.model.TransactionDTO;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExpenseService {
    private final List<TransactionDTO> transactionDTOS = new LinkedList<>();

    public String addExpense(String description, double amount) {
        TransactionDTO transactionDTO = new TransactionDTO(description, amount, LocalDateTime.now());
        transactionDTOS.add(transactionDTO);
        return "Expense added";
    }

    public List<TransactionDTO> getTransactions() {
        return transactionDTOS;
    }

    public Double getSummary() {
        return transactionDTOS.stream().mapToDouble(TransactionDTO::amount).sum();
    }
}
