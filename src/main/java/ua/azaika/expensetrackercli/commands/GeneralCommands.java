package ua.azaika.expensetrackercli.commands;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import ua.azaika.expensetrackercli.model.TransactionDTO;
import ua.azaika.expensetrackercli.services.ExpenseService;

import java.util.List;

@Command
public class GeneralCommands {
    private final ExpenseService expenseService;

    public GeneralCommands(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Command(alias = "add",description = "Add expense")
    public String addExpense(@Option(shortNames = 'd') String description, @Option(shortNames = 'a') double amount) {
        return expenseService.addExpense(description, amount);
    }

    @Command(alias = "list",description = "List all expenses")
    public List<TransactionDTO> listExpenses() {
        return expenseService.getTransactions();
    }

    @Command(alias = "summary", description = "print sum of transactions")
    public Double summary() {
        return  expenseService.getSummary();
    }
}
