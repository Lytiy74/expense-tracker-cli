package ua.azaika.expensetrackercli.services;

import org.springframework.stereotype.Service;
import ua.azaika.expensetrackercli.model.TransactionEntity;
import ua.azaika.expensetrackercli.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
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
                .date(LocalDate.now())
                .build();
        repository.save(entity);
        return "Expense added";
    }

    public List<TransactionEntity> getTransactions() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Double getSummary() {
        List<TransactionEntity> transactions = getTransactions();
        return transactions.stream().mapToDouble(TransactionEntity::getAmount).sum();
    }

    public Double getSummaryByMonth(Month month) {
        LocalDate start = YearMonth.of(LocalDate.now().getYear(), month).atDay(1);
        LocalDate end = YearMonth.of(LocalDate.now().getYear(), month).atEndOfMonth();
        List<TransactionEntity> transactionByMonth = repository.findTransactionByMonth(start, end);
        return transactionByMonth.stream().mapToDouble(TransactionEntity::getAmount).sum();
    }
}
