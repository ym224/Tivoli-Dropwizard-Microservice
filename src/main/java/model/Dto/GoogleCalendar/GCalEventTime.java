package model.Dto.GoogleCalendar;

import java.sql.Timestamp;
import java.util.Objects;

public class GCalEventTime {

    private Timestamp dateTime;

    public GCalEventTime(){}

    public GCalEventTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GCalEventTime that = (GCalEventTime) o;
        return Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateTime);
    }
}
