package resource;

import dao.EventUserDAO;
import dao.NoteDAO;
import model.Dto.EventDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;

@Path("/userEvent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEventResource {

    private NoteDAO noteDAO;
    private EventUserDAO eventUserDAO;

    public UserEventResource(NoteDAO noteDAO, EventUserDAO eventUserDAO) {
        this.noteDAO = noteDAO;
        this.eventUserDAO = eventUserDAO;
    }

    @GET
    public List<EventDto> getEventsForUser(@QueryParam("userId") Long userId) {
        List<EventDto> events = eventUserDAO.findEventsByUserId(userId);

        for (EventDto event: events) {
            event.setAgendas(noteDAO.findAgendasByEventId(event.getId()));
            event.setAttendees(eventUserDAO.getUsersFromEvent(event.getId()));
        }
        return events;
    }

    @GET
    @Path("/tagged")
    public Response getEventsForUserAndTag(@QueryParam("userId") Long userId, @QueryParam("tag") String tag){
        //TODO implement
        return Response.ok().build();
    }


    @POST
    @Path("/tag/{tag}")
    public Response tagUserEvent(@PathParam("tag") String tag,
                                 @QueryParam("userId") Long userId, @QueryParam("eventId") Long eventId) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        return Response.ok().build();
    }
}

/*
To get around the CORS issue:
        return Response.ok().entity(new GenericEntity<List<EventDto>>(events) {})
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .build();
 */