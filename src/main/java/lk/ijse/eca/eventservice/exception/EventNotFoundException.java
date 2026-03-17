package lk.ijse.eca.eventservice.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String eventId) {
        super("Event not found with ID: " + eventId);
    }
}
