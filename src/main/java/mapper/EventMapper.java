package mapper;


import model.Dto.EventDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements ResultSetMapper<EventDto>{
    @Override
    public EventDto map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        EventDto event =  new EventDto(rs.getLong("id"), rs.getString("title"),
                rs.getString("description"), rs.getString("status"),
                rs.getString("creator_email"), rs.getString("video_link"),
                rs.getString("location"), rs.getTimestamp("start_time"),
                rs.getTimestamp("end_time"), rs.getTimestamp("created_time"),
                rs.getTimestamp("last_updated"));
        return event;
    }
}
