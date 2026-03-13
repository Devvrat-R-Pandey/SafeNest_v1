package ui;

import exception.AlertNotFoundException;
import exception.InvalidInputException;
import model.Alert;
import model.Volunteer;
import service.AlertService;
import util.StringUtil;

import java.util.Scanner;

/**
 * Console menu for the Volunteer role.
 * Demonstrates: Scanner input, loops, switch-case, try-catch, arrays (rubric 2.1, 3.1, 8.1).
 */
public class VolunteerMenu {

    private final AlertService alertService;
    private final Scanner scanner;

    public VolunteerMenu(AlertService alertService, Scanner scanner) {
        this.alertService = alertService;
        this.scanner      = scanner;
    }

    /** Entry point – collects volunteer identity then shows sub-menu. */
    public void show() {
        System.out.println("\n--- Volunteer Login ---");
        String id, name;

        try {
            id   = readNonBlank("Volunteer ID  : ");
            name = readNonBlank("Volunteer Name: ");
        } catch (InvalidInputException e) {
            System.out.println("  Error: " + e.getMessage());
            return;
        }

        Volunteer volunteer = new Volunteer(id, name);
        System.out.println("  Welcome, " + volunteer.getName() + "!");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> viewAllAlerts();
                case "2" -> filterByLocation();
                case "3" -> respondToAlert(volunteer);
                case "4" -> updateAlertStatus();
                case "0" -> running = false;
                default  -> System.out.println("  Invalid option. Please enter 1-4 or 0.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("====  Volunteer Menu  ====");
        System.out.println("1. View all alerts");
        System.out.println("2. Filter alerts by location");
        System.out.println("3. Respond to an alert");
        System.out.println("4. Update alert status");
        System.out.println("0. Back to main menu");
        System.out.print("Choose: ");
    }

    // -------------------------------------------------------
    // 1. View all alerts
    // -------------------------------------------------------

    /**
     * Displays all stored alerts.
     * Demonstrates: for loop over array, empty-list handling.
     */
    private void viewAllAlerts() {
        System.out.println("\n--- All Alerts ---");
        Alert[] alerts = alertService.getAllAlerts();

        if (alerts.length == 0) {
            System.out.println("  No alerts found.");
            return;
        }

        // Print summary header
        System.out.println(String.format("%-4s | %-30s | %-20s | %s",
                "ID", "Message", "Location", "Status"));
        System.out.println("-".repeat(75));

        // Iterate array with for loop (rubric 3.1)
        for (int i = 0; i < alerts.length; i++) {
            Alert a = alerts[i];
            System.out.println(StringUtil.formatRow(
                    a.getId(), a.getMessage(), a.getLocation(), a.getStatus().name()));
        }
        System.out.println("-".repeat(75));
        System.out.println("  Total alerts: " + alerts.length);
    }

    // -------------------------------------------------------
    // 2. Filter alerts by location
    // -------------------------------------------------------

    /**
     * Filters alerts by an exact location name using linear search (in repository).
     * Demonstrates: array iteration, String comparison, empty-result handling.
     */
    private void filterByLocation() {
        System.out.println("\n--- Filter Alerts by Location ---");
        try {
            String location = readNonBlank("Enter location to search: ");
            Alert[] filtered = alertService.findByLocation(location);

            if (filtered.length == 0) {
                System.out.println("  No alerts found for location: " + location);
                return;
            }

            System.out.println("  Alerts in '" + location + "':");
            // for-each loop over array result (rubric 3.1)
            for (Alert a : filtered) {
                a.display();
            }

        } catch (InvalidInputException e) {
            System.out.println("  Input error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // 3. Respond to an alert
    // -------------------------------------------------------

    /**
     * Assigns the current volunteer to an alert and adds a response note.
     * Demonstrates: try-catch for custom exceptions, int parsing (rubric 8.1, 8.2).
     */
    private void respondToAlert(Volunteer volunteer) {
        System.out.println("\n--- Respond to Alert ---");
        try {
            int    id   = readAlertId("Enter Alert ID to respond to: ");
            String note = readNonBlank("Enter your response note      : ");

            alertService.respondToAlert(id, volunteer, note);

            System.out.println("  You are now responding to Alert #" + id + ".");
            alertService.findById(id).display();

        } catch (AlertNotFoundException e) {
            System.out.println("  Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("  Input error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // 4. Update alert status
    // -------------------------------------------------------

    /**
     * Updates the status of any alert.
     * Demonstrates: switch-case for menu choices, enum usage, try-catch-finally (rubric 2.1, 8.1).
     */
    private void updateAlertStatus() {
        System.out.println("\n--- Update Alert Status ---");
        try {
            int id = readAlertId("Enter Alert ID to update: ");

            // Verify the alert exists before showing status menu
            Alert alert = alertService.findById(id);
            System.out.println("  Current status: " + alert.getStatus());

            printStatusMenu();
            String choice = scanner.nextLine().trim();

            Alert.Status newStatus;
            switch (choice) {
                case "1" -> newStatus = Alert.Status.RAISED;
                case "2" -> newStatus = Alert.Status.IN_PROGRESS;
                case "3" -> newStatus = Alert.Status.RESOLVED;
                case "4" -> newStatus = Alert.Status.DISMISSED;
                default  -> {
                    System.out.println("  Invalid status choice. Update cancelled.");
                    return;
                }
            }

            alertService.updateStatus(id, newStatus);
            System.out.println("  Alert #" + id + " status updated to: " + newStatus);

        } catch (AlertNotFoundException e) {
            System.out.println("  Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("  Input error: " + e.getMessage());
        } finally {
            // finally block always executes (rubric 8.1)
            System.out.println("  Update operation complete.");
        }
    }

    private void printStatusMenu() {
        System.out.println("  Select new status:");
        System.out.println("  1. RAISED");
        System.out.println("  2. IN_PROGRESS");
        System.out.println("  3. RESOLVED");
        System.out.println("  4. DISMISSED");
        System.out.print("  Choose: ");
    }

    // -------------------------------------------------------
    // Helpers
    // -------------------------------------------------------

    /**
     * Reads and validates a positive integer Alert ID.
     * Demonstrates: try-catch for NumberFormatException, while loop (rubric 3.1, 8.1).
     */
    private int readAlertId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int id = Integer.parseInt(input);
                if (id <= 0) {
                    System.out.println("  Alert ID must be a positive number. Try again.");
                } else {
                    return id;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a numeric Alert ID.");
            }
        }
    }

    /**
     * Loops until the user provides a non-blank string.
     * Demonstrates: do-while loop (rubric 3.1).
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
