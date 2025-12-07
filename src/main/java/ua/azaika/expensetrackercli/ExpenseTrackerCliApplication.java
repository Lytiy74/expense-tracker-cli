package ua.azaika.expensetrackercli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@CommandScan
public class ExpenseTrackerCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerCliApplication.class, args);
    }

}
