package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class EventComment implements Serializable{

    private Long id;
    private Long eventId;
    private Long creatorId;
    private String comment;
    private Timestamp lastUpdated;

    public EventComment(Long eventId, Long creatorId, String comment) {
        this.eventId = eventId;
        this.creatorId = creatorId;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventComment that = (EventComment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eventId, that.eventId) &&
                Objects.equals(creatorId, that.creatorId) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventId, creatorId, comment, lastUpdated);
    }
}
