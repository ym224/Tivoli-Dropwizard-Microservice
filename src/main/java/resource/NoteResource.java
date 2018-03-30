package resource;

import dao.EventDAO;
import dao.NoteDAO;
import dao.UserDAO;
import model.Agenda;
import model.EventNote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/note")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {

    private EventDAO eventDAO;
    private NoteDAO noteDAO;

    public NoteResource(EventDAO eventDAO, NoteDAO noteDAO) {
        this.eventDAO = eventDAO;
        this.noteDAO = noteDAO;
    }

    @GET
    public List<EventNote> getNotes(@QueryParam("eventId") Long eventId, @QueryParam("userId") Long userId) {
        return getNotesForEventAndUser(eventId, userId);
    }

    @POST
    @Path("/agenda")
    public Response AddAgendaToEvent(Agenda agenda) {
        noteDAO.addAgenda(agenda.getEventId(), agenda.getTitle(), agenda.getDescription(), agenda.getDuration(),
                agenda.getCreatorId(), new Timestamp(System.currentTimeMillis()));

        return Response.ok().build();
    }


    @POST
    public Response addNoteToEvent(EventNote note) {
        // default new note to public???
        noteDAO.addNote(note.getEventId(), note.getAgendaId(), note.getNote(), note.getRank(), note.getIsPrivate(),
                note.getIsKeyDecision(), note.getCreatorId(), note.getAssigneeId(), new Timestamp(System.currentTimeMillis()));

        return Response.ok().build();
    }

    //TODO: endpoint for updating notes

    //TODO: endpoint for sorting notes


    protected List<EventNote> getNotesForEventAndUser(Long eventId, Long userId) {
        List<EventNote> notes = new ArrayList<>();

        // get all public notes for event
        notes.addAll(noteDAO.findNotesByEventId(eventId));

        // get all private notes for event with creatorId = userId
        notes.addAll(noteDAO.findNotesByEventIdAndUserId(eventId, userId));

        return notes;
    }
}
