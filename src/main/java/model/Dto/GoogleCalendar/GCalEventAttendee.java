package model.Dto.GoogleCalendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GCalEventAttendee {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String responseStatus;
    private String email;
    private String displayName;

    public GCalEventAttendee(){}

    public GCalEventAttendee(String responseStatus, String email, String displayName) {
        this.responseStatus = responseStatus;
        this.email = email;
        this.displayName = displayName;
    }


    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GCalEventAttendee that = (GCalEventAttendee) o;
        return Objects.equals(responseStatus, that.responseStatus) &&
                Objects.equals(email, that.email) &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(responseStatus, email, displayName);
    }
}
