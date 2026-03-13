package model;

/**
 * Represents a woman raising a safety alert.
 * Demonstrates: inheritance, super keyword, constructor overloading.
 */
public class AlertSender extends Person {

    private String phoneNumber;

    /** Overloaded constructor 1 – with phone number. */
    public AlertSender(String id, String name, String phoneNumber) {
        super(id, name);                // super keyword
        this.phoneNumber = phoneNumber;
    }

    /** Overloaded constructor 2 – without phone number. */
    public AlertSender(String id, String name) {
        this(id, name, "N/A");          // constructor chaining using this()
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String role() {
        return "AlertSender";
    }

    @Override
    public String toString() {
        return super.toString() + " [phone=" + phoneNumber + "]";
    }
}
