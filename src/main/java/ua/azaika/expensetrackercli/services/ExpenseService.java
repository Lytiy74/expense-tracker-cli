package ua.azaika.expensetrackercli.services;

import org.springframework.stereotype.Service;
import ua.azaika.expensetrackercli.mapper.TransactionMapper;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.model.TransactionEntity;
import ua.azaika.expensetrackercli.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@Service
public class ExpenseService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public ExpenseService(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TransactionDTO addExpense(TransactionDTO dto) {
        TransactionEntity entity = mapper.toEntity(dto);
        entity.setDate(LocalDate.now());
        TransactionEntity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public List<TransactionDTO> getTransactions() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No such transaction with id: " + id);
        }
        repository.deleteById(id);
    }

    public TransactionDTO updateById(TransactionDTO dto) {
        TransactionEntity entity = repository.findById(dto.id()).orElseThrow(() -> new IllegalArgumentException("Transaction with id " + dto.id() + " not found"));
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    public Double getSummary() {
        return repository.sumAllAmounts();
    }

    public Double getSummaryByMonth(Month month) {
        LocalDate start = YearMonth.of(LocalDate.now().getYear(), month).atDay(1);
        LocalDate end = YearMonth.of(LocalDate.now().getYear(), month).atEndOfMonth();
        List<TransactionEntity> transactionByMonth = repository.findTransactionByMonth(start, end);
        return transactionByMonth.stream().mapToDouble(TransactionEntity::getAmount).sum();
    }
}
