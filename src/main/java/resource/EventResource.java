package resource;

import dao.EventDAO;
import dao.EventUserDAO;
import dao.NoteDAO;
import dao.UserDAO;
import model.Dto.EventDto;
import model.Dto.GoogleCalendar.GCalEventAttendee;
import model.Dto.GoogleCalendar.GCalEventDto;
import model.EventNote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private EventDAO eventDAO;
    private UserDAO userDAO;
    private NoteDAO noteDAO;
    private EventUserDAO eventUserDAO;

    public EventResource(EventDAO eventDAO, UserDAO userDAO, NoteDAO noteDAO, EventUserDAO eventUserDAO) {
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
        this.noteDAO = noteDAO;
        this.eventUserDAO = eventUserDAO;
    }

    @GET
    public EventDto getEventForUser(@QueryParam("userId") Long userId, @QueryParam("eventId") Long eventId) {
        EventDto event = eventDAO.getEvent(eventId);

        event.setAttendees(eventUserDAO.getUsersFromEvent(eventId));

        event.setAgendas(noteDAO.findAgendasByEventId(eventId));
        // get public notes and private notes belonging to user
        event.setNotes(getNotesForEventAndUser(eventId, userId));

        return event;
    }

    @POST
    public Response addEvent(List<GCalEventDto> events) {
        for (GCalEventDto event : events) {
            // add event only if gcal id does not exist
            if (eventDAO.getEventByGCalId(event.getId()) != null) {
                continue;
            }
            Long eventId = eventDAO.addEvent(event.getId(), event.getSummary(), event.getDescription(), event.getStatus(),
                    event.getCreator().getEmail(), event.getHangoutLink(), event.getLocation(), event.getStart().getDateTime(),
                    event.getEnd().getDateTime(), event.getCreated(), event.getUpdated());

            // TODO: persist creator
            if (event.getAttendees() == null) {
                continue;
            }
            for (GCalEventAttendee attendee : event.getAttendees()) {
                Long userId = -1L;
                if (!attendee.getEmail().equals(event.getCreator().getEmail())) {
                    // persist user
                    userId = getOrAddUser(attendee);
                }
                eventUserDAO.insert(eventId, userId, new Timestamp(System.currentTimeMillis()));
            }
        }
        return Response.ok().build();
    }

    //TODO: update event


    protected Long getOrAddUser(GCalEventAttendee attendee){
        Long userId = userDAO.findUserByEmail(attendee.getEmail());

        if (userId == null && attendee.getDisplayName() != null) {
            String[] attendeeToken = attendee.getDisplayName().split(" ");
            userId = userDAO.addUser(
                    attendeeToken[0], attendeeToken[attendeeToken.length - 1], null,
                    null, attendee.getEmail());

        }
        return userId;
    }

    protected List<EventNote> getNotesForEventAndUser(Long eventId, Long userId) {
        List<EventNote> notes = new ArrayList<>();

        // get all public notes for event
        notes.addAll(noteDAO.findNotesByEventId(eventId));

        // get all private notes for event with creatorId = userId
        notes.addAll(noteDAO.findNotesByEventIdAndUserId(eventId, userId));

        return notes;
    }
}
