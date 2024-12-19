package com.example.rendez_vousservice.beans;

public class Rendez_vous {
    private Long id;
    private String eventTitle;
    private String startDateTime;
    private String endDateTime;
    private String description;
    private String location;
    private Long praticien_Id;
    private String GoogleCalendarLink;

    public Rendez_vous(Long id, String eventTitle, String startDateTime, String endDateTime, String description, String location, Long praticien_Id, String GoogleCalendarLink) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.location = location;
        this.praticien_Id = praticien_Id;
        this.GoogleCalendarLink = GoogleCalendarLink;
    }

    public String getGoogleCalendarLink() {
        return GoogleCalendarLink;
    }
    public void setGoogleCalendarLink(String googleCalendarLink) {
        GoogleCalendarLink = googleCalendarLink;
    }
    public Long getPraticien_Id() {
        return praticien_Id;
    }
    public void setPraticien_Id(Long praticien_Id) {
        this.praticien_Id = praticien_Id;
    }

    public Rendez_vous() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public String getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
