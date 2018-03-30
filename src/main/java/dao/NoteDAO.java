package dao;

import mapper.AgendaMapper;
import mapper.NoteMapper;
import model.Agenda;
import model.EventNote;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.List;

public interface NoteDAO {
    @SqlQuery("insert into event_note (event_id, agenda_id, note, rank, is_private, is_key_decision, creator_id, assignee_id, last_updated) " +
            "values (:eventId, :agendaId, :note, :rank, :isPrivate, :isKeyDecision, :creatorId, :assigneeId, :lastUpdated) returning id")
    Long addNote(@Bind("eventId") Long eventId, @Bind("agendaId") Long agendaId, @Bind("note") String note, @Bind("rank") Long rank,
                 @Bind("isPrivate") Boolean isPrivate, @Bind("isKeyDecision") Boolean isKeyDecision,
                 @Bind("creatorId") Long creatorId, @Bind("assigneeId") Long assigneeId, @Bind("lastUpdated") Timestamp lastUpdated);

    @SqlQuery("insert into agenda (event_id, title, description, duration, creator_id, last_updated) " +
            "values (:eventId, :title, :description, :duration, :creatorId, :lastUpdated) returning id")
    Long addAgenda(@Bind("eventId") Long eventId, @Bind("title") String title, @Bind("description") String description,
                 @Bind("duration") Integer duration, @Bind("creatorId") Long creatorId, @Bind("lastUpdated") Timestamp lastUpdated);

    @RegisterMapper(AgendaMapper.class)
    @SqlQuery("select id, title, description, duration, creator_id from agenda where event_id = :eventId")
    List<Agenda> findAgendasByEventId(@Bind("eventId") long eventId);

    @RegisterMapper(NoteMapper.class)
    // find all public notes
    @SqlQuery("select en.id, en.event_id, a.id as agenda_id, a.title, a.description, a.duration, en.rank, en.is_private, en.is_key_decision, en.creator_id, en.assignee_id, en.note " +
            "from event_note en inner join agenda a on en.agenda_id = a.id where en.event_id = :eventId and is_private = false")
    List<EventNote> findNotesByEventId(@Bind("eventId") long eventId);


    @RegisterMapper(NoteMapper.class)
    // find private notes for user and event
    @SqlQuery("select en.id, en.event_id, a.id as agenda_id, a.title, a.description, a.duration, en.rank, en.is_private, en.is_key_decision, en.creator_id, en.assignee_id, en.note " +
            "from event_note en inner join agenda a on en.agenda_id = a.id " +
            "where en.event_id = :eventId and is_private = true and en.creator_id = :userId")
    List<EventNote> findNotesByEventIdAndUserId(@Bind("eventId") Long eventId, @Bind("userId") Long userId);
}
