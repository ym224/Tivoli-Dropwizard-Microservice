package mapper;

import model.Cal_User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<Cal_User> {
    @Override
    public Cal_User map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        Cal_User calUser = new Cal_User(rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("gender"), rs.getInt("age"), rs.getString("email"));
                calUser.setId(rs.getLong("id"));

        return calUser;
    }
}
