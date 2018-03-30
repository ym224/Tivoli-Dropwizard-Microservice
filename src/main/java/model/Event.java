package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Event implements Serializable{

    private Long id;
    private String title;
    private String description;
    private String status;
    private String creatorEmail;
    private String videoLink;
    private String location;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdTime;
    private Timestamp lastUpdated;

    public Event(){}

    public Event(Long id, String title, String description, String status, String creatorEmail,
                 Timestamp startTime, Timestamp endTime, Timestamp createdTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorEmail = creatorEmail;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdTime = createdTime; // time event or meeting was created
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                Objects.equals(status, event.status) &&
                Objects.equals(creatorEmail, event.creatorEmail) &&
                Objects.equals(videoLink, event.videoLink) &&
                Objects.equals(location, event.location) &&
                Objects.equals(startTime, event.startTime) &&
                Objects.equals(endTime, event.endTime) &&
                Objects.equals(createdTime, event.createdTime) &&
                Objects.equals(lastUpdated, event.lastUpdated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, status, creatorEmail, videoLink, location, startTime, endTime, createdTime, lastUpdated);
    }
}
