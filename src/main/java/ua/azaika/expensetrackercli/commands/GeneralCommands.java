package ua.azaika.expensetrackercli.commands;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import ua.azaika.expensetrackercli.mapper.TransactionMapper;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.model.TransactionEntity;
import ua.azaika.expensetrackercli.services.ExpenseService;

import java.time.Month;
import java.util.List;

@Command
public class GeneralCommands {
    private final ExpenseService expenseService;
    private final TransactionMapper mapper;

    public GeneralCommands(ExpenseService expenseService, TransactionMapper mapper) {
        this.expenseService = expenseService;
        this.mapper = mapper;
    }

    @Command(alias = "add",description = "Add expense")
    public String addExpense(@Option(shortNames = 'd') String description, @Option(shortNames = 'a') double amount) {
        return expenseService.addExpense(description, amount);
    }

    @Command(alias = "list",description = "List all expenses")
    public String listExpenses() {
        List<TransactionEntity> transactions = expenseService.getTransactions();
        List<TransactionDTO> transactionDTOS = transactions.stream().map(mapper::toDto).toList();
        StringBuilder stringBuilder = new StringBuilder();
        transactionDTOS.forEach(transactionDTO -> stringBuilder.append(transactionDTO).append("\n"));
        return stringBuilder.toString();
    }

    @Command(alias = "summary", description = "print sum of transactions")
    public Double summary(@Option(shortNames = 'm') Month month) {
        if (month == null) {
            return expenseService.getSummary();
        }
        return expenseService.getSummaryByMonth(month);
    }

    @Command(alias = "delete", description = "delete expense by id")
    public void deleteById(Integer id) {
        expenseService.deleteById(id);
    }
}
