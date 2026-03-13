package ui;

import exception.InvalidInputException;
import model.Alert;
import service.AlertService;
import util.StringUtil;

import java.util.Scanner;

/**
 * Console menu for the Alert Sender role.
 * Demonstrates: Scanner input, loops, conditionals, exception handling (rubric 8.1).
 */
public class AlertSenderMenu {

    private final AlertService alertService;
    private final Scanner scanner;

    public AlertSenderMenu(AlertService alertService, Scanner scanner) {
        this.alertService = alertService;
        this.scanner      = scanner;
    }

    /** Entry point – shows the Alert Sender sub-menu in a loop. */
    public void show() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> raiseAlert();
                case "0" -> running = false;
                default  -> System.out.println("  Invalid option. Please enter 1 or 0.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("====  Alert Sender Menu  ====");
        System.out.println("1. Raise an alert");
        System.out.println("0. Back to main menu");
        System.out.print("Choose: ");
    }

    /** Collects alert details and saves via service. */
    private void raiseAlert() {
        System.out.println("\n--- Raise Alert ---");
        try {
            String message     = readNonBlank("Enter your message (what happened): ");
            String location    = readNonBlank("Enter your location               : ");
            String description = readNonBlank("Enter additional description      : ");

            Alert alert = alertService.createAlert(message, location, description);

            System.out.println("\n  Alert raised successfully!");
            System.out.println("  Your Alert ID: " + alert.getId() + "  (save this to track your alert)");
            alert.display();

        } catch (InvalidInputException e) {
            // Catches custom exception and shows user-friendly message
            System.out.println("  Input error: " + e.getMessage());
        }
    }

    /**
     * Loops until the user enters a non-blank value.
     * Demonstrates: do-while loop, String validation (rubric 3.1, 4.2).
     */
    private String readNonBlank(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (StringUtil.isBlank(input)) {
                System.out.println("  This field cannot be blank. Please try again.");
            }
        } while (StringUtil.isBlank(input));
        return input.trim();
    }
}
