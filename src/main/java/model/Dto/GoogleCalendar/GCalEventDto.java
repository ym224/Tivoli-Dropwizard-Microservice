package model.Dto.GoogleCalendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// event response from gcal apis
@JsonIgnoreProperties(ignoreUnknown = true)
public class GCalEventDto {

    @JsonIgnoreProperties(ignoreUnknown = true)
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String summary;
    private String description;
    private String status;
    private GCalEventCreator creator;
    private String hangoutLink;
    private String location;
    private GCalEventTime start;
    private GCalEventTime end;
    private Timestamp created;
    private Timestamp updated;
    private List<GCalEventAttendee> attendees;

    public GCalEventDto(){}

    public GCalEventDto(String id, String summary, String description, String status, GCalEventCreator creator,
                        String hangoutLink, String location, GCalEventTime start, GCalEventTime end,
                        Timestamp created, Timestamp updated) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.creator = creator;
        this.hangoutLink = hangoutLink;
        this.location = location;
        this.start = start;
        this.end = end;
        this.created = created;
        this.updated = updated;
        attendees = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public GCalEventCreator getCreator() {
        return creator;
    }

    public void setCreator(GCalEventCreator creator) {
        this.creator = creator;
    }

    public String getHangoutLink() {
        return hangoutLink;
    }

    public void setHangoutLink(String hangoutLink) {
        this.hangoutLink = hangoutLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GCalEventTime getStart() {
        return start;
    }

    public void setStart(GCalEventTime start) {
        this.start = start;
    }

    public GCalEventTime getEnd() {
        return end;
    }

    public void setEnd(GCalEventTime end) {
        this.end = end;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public List<GCalEventAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<GCalEventAttendee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GCalEventDto that = (GCalEventDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(hangoutLink, that.hangoutLink) &&
                Objects.equals(location, that.location) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(attendees, that.attendees);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summary, description, status, creator, hangoutLink, location, start, end, created, updated, attendees);
    }
}
