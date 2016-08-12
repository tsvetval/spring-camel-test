package ru.camel.example.model;

/**
 *
 */
public class PaymentStatus {
    public enum Actor {AGENT, COTT, ADMIN}

    public enum Status {OK, ANNULLED, IN_PROGRESS}

    public enum Resolution {OK, WRITE_ERROR, ERROR, FAULT, CANCELED}

    private Actor actor;
    private Status status;
    private Resolution resolution;
    private String errorCode;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
