package mapper;

import model.EventNote;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements ResultSetMapper<EventNote> {
    @Override
    public EventNote map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        EventNote note = new EventNote(rs.getLong("id"), rs.getLong("event_id"),
                rs.getLong("agenda_id"), rs.getString("note"), rs.getLong("creator_id"));
        note.setIsKeyDecision(rs.getBoolean("is_key_decision"));
        note.setIsPrivate(rs.getBoolean("is_private"));
        note.setAssigneeId(rs.getLong("assignee_id"));
        note.setDuration(rs.getInt("duration"));
        note.setTitle(rs.getString("title"));
        note.setDescription(rs.getString("description"));
        return note;
    }
}
