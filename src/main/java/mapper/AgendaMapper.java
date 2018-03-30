package mapper;

import model.Agenda;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgendaMapper implements ResultSetMapper<Agenda> {
    @Override
    public Agenda map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        Agenda agenda = new Agenda();
        agenda.setId(rs.getLong("id"));
        agenda.setCreatorId(rs.getLong("creator_id"));
        agenda.setDuration(rs.getInt("duration"));
        agenda.setTitle(rs.getString("title"));
        agenda.setDescription(rs.getString("description"));
        return agenda;
    }
}
