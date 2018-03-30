package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventNote implements Serializable{

    private Long id;
    private Long eventId;
    private Long agendaId;
    private Long rank; // note sorting
    private String note;
    private Boolean isKeyDecision;
    private Boolean isPrivate;
    private Long assigneeId;
    private Long creatorId;
    private Timestamp lastUpdated;

    private String title;
    private String description;
    private Integer duration;

    public EventNote(){}

    public EventNote(Long id, Long eventId, Long agendaId, String note, Long creatorId) {
        this.id = id;
        this.eventId = eventId;
        this.note = note;
        this.creatorId = creatorId;
        this.agendaId = agendaId;

        // default set to false
        this.isPrivate = false;
        this.isKeyDecision = false;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Boolean getIsKeyDecision() {
        return isKeyDecision;
    }

    public void setIsKeyDecision(Boolean isKeyDecision) {
        isKeyDecision = isKeyDecision;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventNote eventNote = (EventNote) o;
        return Objects.equals(id, eventNote.id) &&
                Objects.equals(eventId, eventNote.eventId) &&
                Objects.equals(agendaId, eventNote.agendaId) &&
                Objects.equals(rank, eventNote.rank) &&
                Objects.equals(note, eventNote.note) &&
                Objects.equals(isKeyDecision, eventNote.isKeyDecision) &&
                Objects.equals(isPrivate, eventNote.isPrivate) &&
                Objects.equals(assigneeId, eventNote.assigneeId) &&
                Objects.equals(creatorId, eventNote.creatorId) &&
                Objects.equals(lastUpdated, eventNote.lastUpdated) &&
                Objects.equals(title, eventNote.title) &&
                Objects.equals(description, eventNote.description) &&
                Objects.equals(duration, eventNote.duration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventId, agendaId, rank, note, isKeyDecision, isPrivate, assigneeId, creatorId, lastUpdated, title, description, duration);
    }
}
