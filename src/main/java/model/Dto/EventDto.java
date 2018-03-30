package model.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Agenda;
import model.Cal_User;
import model.EventNote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {

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
    private List<Cal_User> attendees;
    private List<String> tags;
    private List<Agenda> agendas;
    private List<EventNote> notes;

    public EventDto(Long id, String title, String description, String status, String creatorEmail, String videoLink,
                    String location, Timestamp startTime, Timestamp endTime, Timestamp createdTime,
                    Timestamp lastUpdated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creatorEmail = creatorEmail;
        this.videoLink = videoLink;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdTime = createdTime;
        this.lastUpdated = lastUpdated;
        attendees = new ArrayList<>();
        agendas = new ArrayList<>();
        tags = new ArrayList<>();
        notes = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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

    public List<Cal_User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Cal_User> attendees) {
        this.attendees = attendees;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<EventNote> getNotes() {
        return notes;
    }

    public void setNotes(List<EventNote> notes) {
        this.notes = notes;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) &&
                Objects.equals(title, eventDto.title) &&
                Objects.equals(description, eventDto.description) &&
                Objects.equals(status, eventDto.status) &&
                Objects.equals(creatorEmail, eventDto.creatorEmail) &&
                Objects.equals(videoLink, eventDto.videoLink) &&
                Objects.equals(location, eventDto.location) &&
                Objects.equals(startTime, eventDto.startTime) &&
                Objects.equals(endTime, eventDto.endTime) &&
                Objects.equals(createdTime, eventDto.createdTime) &&
                Objects.equals(lastUpdated, eventDto.lastUpdated) &&
                Objects.equals(attendees, eventDto.attendees) &&
                Objects.equals(tags, eventDto.tags) &&
                Objects.equals(agendas, eventDto.agendas) &&
                Objects.equals(notes, eventDto.notes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, status, creatorEmail, videoLink, location, startTime, endTime, createdTime, lastUpdated, attendees, tags, agendas, notes);
    }
}
