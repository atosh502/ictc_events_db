package com.sankalpa.ictc_events_database.models;

import java.sql.Date;
import java.sql.Time;

public class EventSection {

    private Long eventSectionId;
    private String eventSectionDate;
    private String eventSectionTime;

    public EventSection(){}

    public EventSection(Long eventSectionId, String eventSectionDate, String eventSectionTime) {
        this.eventSectionId = eventSectionId;
        this.eventSectionDate = eventSectionDate;
        this.eventSectionTime = eventSectionTime;
    }

    public EventSection(String eventSectionDate, String eventSectionTime) {
        this.eventSectionDate = eventSectionDate;
        this.eventSectionTime = eventSectionTime;
    }

    public Long getEventSectionId() {
        return eventSectionId;
    }

    public void setEventSectionId(Long eventSectionId) {
        this.eventSectionId = eventSectionId;
    }

    public String getEventSectionDate() {
        return eventSectionDate;
    }

    public void setEventSectionDate(String eventSectionDate) {
        this.eventSectionDate = eventSectionDate;
    }

    public String getEventSectionTime() {
        return eventSectionTime;
    }

    public void setEventSectionTime(String eventSectionTime) {
        this.eventSectionTime = eventSectionTime;
    }
}
