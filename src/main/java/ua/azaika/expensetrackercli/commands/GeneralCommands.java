package ua.azaika.expensetrackercli.commands;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.services.ExpenseService;

import java.time.Month;
import java.util.List;

@Command
public class GeneralCommands {
    private final ExpenseService expenseService;

    public GeneralCommands(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Command(alias = "add",description = "Add expense")
    public String addExpense(@Option(shortNames = 'd') String description, @Option(shortNames = 'a') Double amount) {
        if (description == null || amount == null) {
            return "Invalid arguments";
        }

        TransactionDTO dto = new TransactionDTO(null, description, amount, null);

        return expenseService.addExpense(dto);
    }

    @Command(alias = "list",description = "List all expenses")
    public String listExpenses() {
        List<TransactionDTO> transactions = expenseService.getTransactions();
        StringBuilder stringBuilder = new StringBuilder();
        transactions.forEach(transactionDTO -> stringBuilder.append(transactionDTO).append("\n"));
        return stringBuilder.toString();
    }

    @Command(alias = "summary", description = "print sum of transactions")
    public Double summary(@Option(shortNames = 'm') Month month) {
        if (month == null) {
            return expenseService.getSummary();
        }
        return expenseService.getSummaryByMonth(month);
    }

    @Command(alias = "update", description = "update expense by id")
    public String updateById(@Option(shortNames = 'i') Integer id,
                             @Option(shortNames = 'd') String description,
                             @Option(shortNames = 'a') Double amount) {
        if (id == null || description == null || amount == null) {
            return "Invalid arguments";
        }

        TransactionDTO dto = new TransactionDTO(id, description, amount, null);
        expenseService.updateById(dto);
        return "Expense updated";
    }

    @Command(alias = "delete", description = "delete expense by id")
    public void deleteById(Integer id) {
        expenseService.deleteById(id);
    }
}
