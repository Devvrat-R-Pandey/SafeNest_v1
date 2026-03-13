package model;

/**
 * Represents a volunteer who responds to safety alerts.
 * Demonstrates: inheritance, super keyword, method overriding.
 */
public class Volunteer extends Person {

    private String location; // volunteer's coverage area

    /** Overloaded constructor 1 – with location. */
    public Volunteer(String id, String name, String location) {
        super(id, name);            // super keyword
        this.location = location;
    }

    /** Overloaded constructor 2 – without location. */
    public Volunteer(String id, String name) {
        this(id, name, "Unknown");  // constructor chaining using this()
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String role() {
        return "Volunteer";
    }

    @Override
    public String toString() {
        return super.toString() + " [area=" + location + "]";
    }
}
