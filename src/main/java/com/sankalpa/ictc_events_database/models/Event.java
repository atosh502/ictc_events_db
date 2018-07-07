package com.sankalpa.ictc_events_database.models;

import java.sql.Date;
import java.time.LocalDate;

public class Event  {

    private Long eventId;
    private String eventName;
    private String eventDescription;
    private int expectedNumberOfParticipants;
    private int eventDurationInDays;
    // TODO: Java didn't allow a null value for Date so figure out a way to solve this;
    private String eventStartDate;
    private String eventEndDate;
    private int accepted;

    public Event(){}

    public Event(String eventName, String eventDescription, int eventDurationInDays) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDurationInDays = eventDurationInDays;
    }

    public Event(Long eventId, String eventName, String eventDescription, int expectedNumberOfParticipants,
                 int eventDurationInDays, String eventStartDate, String eventEndDate, int accepted) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.expectedNumberOfParticipants = expectedNumberOfParticipants;
        this.eventDurationInDays = eventDurationInDays;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.accepted = accepted;
    }

    public Event(String eventName, String eventDescription, int expectedNumberOfParticipants, int eventDurationInDays,
                 String eventStartDate, String eventEndDate, int accepted) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.expectedNumberOfParticipants = expectedNumberOfParticipants;
        this.eventDurationInDays = eventDurationInDays;
        this.eventEndDate = eventEndDate;
        this.eventStartDate = eventStartDate;
        this.accepted = accepted;
    }

    public String getEventStartDate(){
        return eventStartDate;
    }

    public void setEventStartDate(String sqlDate) {
        this.eventStartDate = sqlDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String sqlDate) {
        this.eventEndDate = sqlDate;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getEventDurationInDays() {
        return eventDurationInDays;
    }

    public void setEventDurationInDays(int eventDurationInDays) {
        this.eventDurationInDays = eventDurationInDays;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getExpectedNumberOfParticipants() {
        return expectedNumberOfParticipants;
    }

    public void setExpectedNumberOfParticipants(int expectedNumberOfParticipants) {
        this.expectedNumberOfParticipants = expectedNumberOfParticipants;
    }
}
