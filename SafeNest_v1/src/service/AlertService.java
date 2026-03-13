package service;

import model.Alert;
import model.Volunteer;

/**
 * Service interface for alert operations.
 * Demonstrates: interface, method contracts, loose coupling (rubric 7.2).
 */
public interface AlertService {

    /** Creates and stores a new alert. */
    Alert createAlert(String message, String location, String description);

    /** Returns all alerts. */
    Alert[] getAllAlerts();

    /** Finds a single alert by ID; throws AlertNotFoundException if missing. */
    Alert findById(int id);

    /** Finds alerts by exact location name (case-insensitive). */
    Alert[] findByLocation(String location);

    /** Returns alerts sorted by location A-Z (bubble sort). */
    Alert[] getSortedByLocation();

    /** Assigns a volunteer to an alert and sets status to IN_PROGRESS. */
    void respondToAlert(int id, Volunteer volunteer, String note);

    /** Updates the status of an alert. */
    void updateStatus(int id, Alert.Status newStatus);
}
