package exception;

/**
 * Thrown when an alert with a given ID cannot be found.
 * Demonstrates: custom exception, throw/throws.
 */
public class AlertNotFoundException extends RuntimeException {

    private final int alertId;

    public AlertNotFoundException(int id) {
        super("Alert with ID " + id + " was not found.");
        this.alertId = id;
    }

    public int getAlertId() { return alertId; }
}
