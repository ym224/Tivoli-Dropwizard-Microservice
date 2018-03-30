package resource;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import dao.EventDAO;
import dao.EventUserDAO;
import dao.UserDAO;
import model.Dto.EventDto;
import org.apache.log4j.Logger;
import service.GoogleCalendarService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/googleCalendar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalendarResource {

    private GoogleCalendarService googleCalendarService;
    private static Logger LOGGER = Logger.getLogger("CalendarResource Logger");
    private EventDAO eventDAO;
    private UserDAO userDAO;
    private EventUserDAO eventUserDAO;


    public CalendarResource(EventDAO eventDAO, UserDAO userDAO, EventUserDAO eventUserDAO,
                            GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
        this.eventUserDAO = eventUserDAO;
    }

    @GET
    public Long syncGoogleEvents(@QueryParam("userName") String userName,
                                 @QueryParam("maxResults") Integer maxResults) {
        List<Event> gCalEvents = new ArrayList<>();
        Long userId = -1L;
        try {
            gCalEvents = googleCalendarService.buildAuthorizedClientService(maxResults, userName);
        }
        catch (Exception ex) {
            LOGGER.error("Error importing events from GCal.");
            ex.printStackTrace();
        }

        if (gCalEvents.size() > 0) {
            // add or get userId from email
            userId = userDAO.findUserByEmail(userName);
            if ( userId == null) {
                // may need to call a diff api for user metadata
                userId = userDAO.addUser(null, null, null, null, userName);
            }

            for (Event event: gCalEvents) {
                // get event by gcalId
                Long eventId = eventDAO.getEventByGCalId(event.getId());
                try {
                    addOrUpdateEvent(eventId, event);
                }
                catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        return userId;
    }

    protected void addOrUpdateEvent(Long eventId, Event event) throws Exception {
        Timestamp startTime = new Timestamp(event.getStart().getDateTime().getValue());
        Timestamp endTime = new Timestamp(event.getStart().getDateTime().getValue());
        Timestamp createdTime = new Timestamp(event.getCreated().getValue());
        Timestamp updatedTime = new Timestamp(event.getUpdated().getValue());

        // add event if does not exist
        if (eventId == null) {
            eventId = eventDAO.addEvent(event.getId(), event.getSummary(), event.getDescription(), event.getStatus(),
                    event.getCreator().getEmail(), event.getHangoutLink(), event.getLocation(), startTime, endTime,
                    createdTime, updatedTime);
            addOrUpdateAttendees(eventId, event);
        }
        // update event if timestamp greater than stored
        else {
            EventDto eventDto = eventDAO.getEvent(eventId);
            if (eventDto.getLastUpdated().before(updatedTime)) {
                // update event
                eventDAO.updateEvent(eventId, event.getSummary(), event.getDescription(), event.getStatus(), event.getHangoutLink(),
                        event.getLocation(), startTime, endTime, updatedTime);
                addOrUpdateAttendees(eventId, event);
            }
        }
    }

    protected void addOrUpdateAttendees(Long eventId, Event event){
        if (event.getAttendees() == null) return;

        for (EventAttendee attendee: event.getAttendees()) {
            Long userId = userDAO.findUserByEmail(attendee.getEmail());

            // add new attendee to user
            if (userId == null) {
                String[] tokens = null;
                if (attendee.getDisplayName() != null) {
                    tokens = attendee.getDisplayName().split(" ");
                }

                if (tokens != null && tokens.length > 1)  {
                    userId = userDAO.addUser(tokens[0], tokens[tokens.length - 1], null, null, attendee.getEmail());
                }
                else {
                    userId = userDAO.addUser(null, null, null, null, attendee.getEmail());
                }
                eventUserDAO.insert(eventId, userId, new Timestamp(System.currentTimeMillis()));
            }

            // add attendee to event if does not already an attendee
            else if (eventUserDAO.getEventUserFromUserId(eventId, userId) == null){
                eventUserDAO.insert(eventId, userId, new Timestamp(System.currentTimeMillis()));
            }
        }
    }

}