package ua.azaika.expensetrackercli.services;

import org.springframework.stereotype.Service;
import ua.azaika.expensetrackercli.model.Transaction;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExpenseService {
    private final List<Transaction> transactions = new LinkedList<>();

    public String addExpense(String description, double amount) {
        Transaction transaction = new Transaction(description, amount, LocalDateTime.now());
        transactions.add(transaction);
        return "Expense added";
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
