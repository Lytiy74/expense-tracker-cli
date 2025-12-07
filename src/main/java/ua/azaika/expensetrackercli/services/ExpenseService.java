package ua.azaika.expensetrackercli.services;

import org.springframework.stereotype.Service;
import ua.azaika.expensetrackercli.model.TransactionEntity;
import ua.azaika.expensetrackercli.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {
    private final TransactionRepository repository;

    public ExpenseService(TransactionRepository repository) {
        this.repository = repository;
    }

    public String addExpense(String description, double amount) {
        TransactionEntity entity = TransactionEntity.builder()
                .description(description)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(entity);
        return "Expense added";
    }

    public List<TransactionEntity> getTransactions() {
        return repository.findAll();
    }

    public Double getSummary() {
        List<TransactionEntity> transactions = getTransactions();
        return transactions.stream().mapToDouble(TransactionEntity::getAmount).sum();
    }
}
