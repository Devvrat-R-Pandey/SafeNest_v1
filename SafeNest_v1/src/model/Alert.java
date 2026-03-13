package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a safety alert raised by an AlertSender.
 * Demonstrates: encapsulation, enum, constructors, this keyword, getters/setters.
 */
public class Alert {

    public enum Status {
        RAISED, IN_PROGRESS, RESOLVED, DISMISSED
    }

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final int id;
    private String message;
    private String location;
    private String description;
    private Status status;
    private final String timestamp;   // stored as formatted String (String manipulation)
    private Volunteer responder;      // who responded
    private String responseNote;

    public Alert(int id, String message, String location, String description) {
        this.id          = id;
        this.message     = message;
        this.location    = location;
        this.description = description;
        this.status      = Status.RAISED;
        this.timestamp   = LocalDateTime.now().format(FMT);
    }

    // ---------- Getters ----------
    public int       getId()           { return id; }
    public String    getMessage()      { return message; }
    public String    getLocation()     { return location; }
    public String    getDescription()  { return description; }
    public Status    getStatus()       { return status; }
    public String    getTimestamp()    { return timestamp; }
    public Volunteer getResponder()    { return responder; }
    public String    getResponseNote() { return responseNote; }

    // ---------- Setters ----------
    public void setMessage(String message)         { this.message = message; }
    public void setLocation(String location)       { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(Status status)           { this.status = status; }
    public void setResponder(Volunteer responder)  { this.responder = responder; }
    public void setResponseNote(String note)       { this.responseNote = note; }

    /** Formatted display used in console output. */
    public void display() {
        System.out.println("--------------------------------------------------");
        System.out.println("Alert ID   : " + id);
        System.out.println("Message    : " + message);
        System.out.println("Location   : " + location);
        System.out.println("Description: " + description);
        System.out.println("Status     : " + status);
        System.out.println("Raised At  : " + timestamp);
        if (responder != null) {
            System.out.println("Responder  : " + responder.getName()
                             + " (ID: " + responder.getId() + ")");
        }
        if (responseNote != null && !responseNote.trim().isEmpty()) {
            System.out.println("Note       : " + responseNote);
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String toString() {
        return "[" + id + "] " + message + " | " + location + " | " + status;
    }
}
