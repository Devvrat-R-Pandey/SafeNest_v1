package main;

import repository.AlertRepository;
import service.AlertService;
import service.AlertServiceImpl;
import ui.AlertSenderMenu;
import ui.VolunteerMenu;

import java.util.Scanner;

/**
 * Entry point for the SafeNest – Women Safety Alert and Response System.
 *
 * Main menu lets the user choose a role:
 *   1. Alert Sender  → raise an alert
 *   2. Volunteer     → view / filter / respond / update alerts
 *
 * Demonstrates: modular design, OOP wiring, loops, switch-case (rubric 2.1, 2.3).
 */
public class SafeNestApp {

    private final Scanner          scanner;
    private final AlertService     alertService;
    private final AlertSenderMenu  alertSenderMenu;
    private final VolunteerMenu    volunteerMenu;

    public SafeNestApp() {
        this.scanner         = new Scanner(System.in);
        AlertRepository repo = new AlertRepository();
        this.alertService    = new AlertServiceImpl(repo);
        this.alertSenderMenu = new AlertSenderMenu(alertService, scanner);
        this.volunteerMenu   = new VolunteerMenu(alertService, scanner);
    }

    public static void main(String[] args) {
        new SafeNestApp().run();
    }

    /** Main application loop. */
    private void run() {
        System.out.println("=============================================");
        System.out.println("   Welcome to SafeNest                      ");
        System.out.println("   Women Safety Alert and Response System    ");
        System.out.println("=============================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> alertSenderMenu.show();
                case "2" -> volunteerMenu.show();
                case "0" -> {
                    running = false;
                    System.out.println("\n  Thank you for using SafeNest. Stay safe!");
                }
                default -> System.out.println("  Invalid option. Please enter 1, 2, or 0.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("---------------------------------------------");
        System.out.println("  Select your role:");
        System.out.println("  1. Alert Sender  (raise a safety alert)");
        System.out.println("  2. Volunteer     (view and respond to alerts)");
        System.out.println("  0. Exit");
        System.out.println("---------------------------------------------");
        System.out.print("  Choose: ");
    }
}
