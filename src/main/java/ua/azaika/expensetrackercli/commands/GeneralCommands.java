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

    private static final String EXPENSE_ADDED = "Expense with amount %s , description %s added";
    private static final String EXPENSE_HEADER = "%-4s | %-10s | %-25s | %10s\n";
    private static final String EXPENSE_DESCRIPTION = "%-4d | %tY-%<tm-%<td | %-25s | %10.2f\n";

    public GeneralCommands(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Command(alias = "add", description = "Add expense")
    public String addExpense(@Option(shortNames = 'd') String description, @Option(shortNames = 'a') Double amount) {
        if (description == null || amount == null) {
            return "Invalid arguments";
        }

        TransactionDTO dto = new TransactionDTO(null, description, amount, null);

        TransactionDTO transactionDTO = expenseService.addExpense(dto);
        return String.format(EXPENSE_ADDED, transactionDTO.amount(), transactionDTO.description());
    }

    @Command(alias = "list", description = "List all expenses")
    public String listExpenses() {
        List<TransactionDTO> transactions = expenseService.getTransactions();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(EXPENSE_HEADER, "id", "date", "description", "amount"));
        transactions.forEach(t -> stringBuilder.append(String.format(EXPENSE_DESCRIPTION, t.id(), t.date(), t.description(), t.amount())));
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
        if (id == null || (description == null && amount == null)) {
            return "Invalid arguments";
        }

        TransactionDTO dto = new TransactionDTO(id, description, amount, null);
        expenseService.updateById(dto);
        return "Expense updated";
    }

    @Command(alias = "delete", description = "delete expense by id")
    public String deleteById(@Option(shortNames = 'i') Integer id) {
        if (id == null) {
            return "Invalid arguments";
        }
        expenseService.deleteById(id);
        return "Expense deleted";
    }
}
