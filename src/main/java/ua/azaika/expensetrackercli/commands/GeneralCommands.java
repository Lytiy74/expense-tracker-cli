package ua.azaika.expensetrackercli.commands;

import org.springframework.shell.command.annotation.Command;
import ua.azaika.expensetrackercli.model.Transaction;
import ua.azaika.expensetrackercli.services.ExpenseService;

import java.util.List;

@Command
public class GeneralCommands {
    private final ExpenseService expenseService;

    public GeneralCommands(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Command(alias = "add",description = "Add expense")
    public String addExpense(String description, double amount) {
        return expenseService.addExpense(description, amount);
    }

    @Command(alias = "list",description = "List all expenses")
    public List<Transaction> listExpenses() {
        return expenseService.getTransactions();
    }
}
