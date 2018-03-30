package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agenda implements Serializable {

    private Long id;
    private Long eventId;
    private String title;
    private String description;
    private Long creatorId;
    private Integer duration;
    private Timestamp lastUpdated;

    public Agenda(){}

    public Agenda(Long eventId, String title, String description, Long creatorId, Integer duration){
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.duration = duration;
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return Objects.equals(id, agenda.id) &&
                Objects.equals(eventId, agenda.eventId) &&
                Objects.equals(title, agenda.title) &&
                Objects.equals(description, agenda.description) &&
                Objects.equals(creatorId, agenda.creatorId) &&
                Objects.equals(duration, agenda.duration) &&
                Objects.equals(lastUpdated, agenda.lastUpdated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventId, title, description, creatorId, duration, lastUpdated);
    }
}
