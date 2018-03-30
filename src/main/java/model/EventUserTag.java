package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class EventUserTag implements Serializable {

    private Long id;
    private Long eventId;
    private Long userId;
    private String tag;
    private Timestamp lastUpdated;

    public EventUserTag(Long eventId, Long userId, String tag) {
        this.eventId = eventId;
        this.userId = userId;
        this.tag = tag;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        EventUserTag that = (EventUserTag) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eventId, that.eventId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventId, userId, tag, lastUpdated);
    }
}
