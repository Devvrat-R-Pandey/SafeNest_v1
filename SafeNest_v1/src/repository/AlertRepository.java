package repository;

import exception.AlertNotFoundException;
import model.Alert;

/**
 * In-memory array-based storage for alerts.
 * Demonstrates: arrays, linear search, bubble sort (rubric 3.2, 4.1).
 */
public class AlertRepository {

    private static final int MAX_ALERTS = 50;
    private final Alert[] alerts = new Alert[MAX_ALERTS];
    private int count = 0;

    // ---------- CREATE ----------

    /** Adds an alert to the array. */
    public void save(Alert alert) {
        if (count >= MAX_ALERTS) {
            System.out.println("Alert storage is full. Cannot add more alerts.");
            return;
        }
        alerts[count] = alert;
        count++;
    }

    // ---------- READ ----------

    /** Returns a copy of all stored alerts (no nulls). */
    public Alert[] findAll() {
        Alert[] result = new Alert[count];
        for (int i = 0; i < count; i++) {
            result[i] = alerts[i];
        }
        return result;
    }

    /**
     * Linear Search by ID.
     * Demonstrates: linear search algorithm (rubric 4.1).
     */
    public Alert findById(int id) {
        for (int i = 0; i < count; i++) {       // linear search
            if (alerts[i].getId() == id) {
                return alerts[i];
            }
        }
        throw new AlertNotFoundException(id);
    }

    /**
     * Linear search: find alerts matching a location (case-insensitive).
     * Returns array of matching alerts.
     */
    public Alert[] findByLocation(String location) {
        // First pass – count matches
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (alerts[i].getLocation().equalsIgnoreCase(location)) {
                matchCount++;
            }
        }
        // Second pass – collect matches
        Alert[] result = new Alert[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (alerts[i].getLocation().equalsIgnoreCase(location)) {
                result[index++] = alerts[i];
            }
        }
        return result;
    }

    /**
     * Bubble Sort by location name A-Z.
     * Returns a NEW sorted array (does not modify original order).
     * Demonstrates: bubble sort algorithm (rubric 4.1).
     */
    public Alert[] sortedByLocation() {
        Alert[] sorted = findAll();             // work on a copy
        int n = sorted.length;
        for (int i = 0; i < n - 1; i++) {      // bubble sort
            for (int j = 0; j < n - i - 1; j++) {
                String locA = sorted[j].getLocation().toLowerCase();
                String locB = sorted[j + 1].getLocation().toLowerCase();
                if (locA.compareTo(locB) > 0) {
                    Alert temp    = sorted[j];
                    sorted[j]     = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    /** Checks whether any alerts exist. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Returns current alert count. */
    public int getCount() {
        return count;
    }

    /** Returns next available alert ID (count + 1). */
    public int nextId() {
        return count + 1;
    }
}
