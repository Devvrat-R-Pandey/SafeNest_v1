package service;

import exception.InvalidInputException;
import model.Alert;
import model.Volunteer;
import repository.AlertRepository;
import util.StringUtil;

/**
 * Implements AlertService.
 * Demonstrates: interface implementation, exception propagation, String manipulation.
 */
public class AlertServiceImpl implements AlertService {

    private final AlertRepository repository;

    public AlertServiceImpl(AlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public Alert createAlert(String message, String location, String description) {
        // Validate inputs (throw custom exception if blank)
        if (StringUtil.isBlank(message)) {
            throw new InvalidInputException("Message cannot be blank.");
        }
        if (StringUtil.isBlank(location)) {
            throw new InvalidInputException("Location cannot be blank.");
        }
        if (StringUtil.isBlank(description)) {
            throw new InvalidInputException("Description cannot be blank.");
        }

        // String manipulation – clean and capitalise
        String cleanMsg  = StringUtil.cleanSentence(message);
        String cleanLoc  = StringUtil.cleanSentence(location);
        String cleanDesc = StringUtil.cleanSentence(description);

        int id = repository.nextId();
        Alert alert = new Alert(id, cleanMsg, cleanLoc, cleanDesc);
        repository.save(alert);
        return alert;
    }

    @Override
    public Alert[] getAllAlerts() {
        return repository.findAll();
    }

    @Override
    public Alert findById(int id) {
        // AlertNotFoundException propagates from repository (throws)
        return repository.findById(id);
    }

    @Override
    public Alert[] findByLocation(String location) {
        if (StringUtil.isBlank(location)) {
            throw new InvalidInputException("Location filter cannot be blank.");
        }
        return repository.findByLocation(location.trim());
    }

    @Override
    public Alert[] getSortedByLocation() {
        return repository.sortedByLocation();
    }

    @Override
    public void respondToAlert(int id, Volunteer volunteer, String note) {
        Alert alert = repository.findById(id);          // throws if not found
        alert.setResponder(volunteer);
        alert.setResponseNote(StringUtil.cleanSentence(note));
        alert.setStatus(Alert.Status.IN_PROGRESS);
    }

    @Override
    public void updateStatus(int id, Alert.Status newStatus) {
        Alert alert = repository.findById(id);          // throws if not found
        alert.setStatus(newStatus);
    }
}
