package dao;

import mapper.EventMapper;
import mapper.UserMapper;
import model.Cal_User;
import model.Dto.EventDto;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.xml.ws.RequestWrapper;
import java.sql.Timestamp;
import java.util.List;

public interface EventUserDAO {
    @SqlQuery("insert into event_user (event_id, user_id) " +
            "values (:eventId, :userId) returning id")
    Long insert(@Bind("eventId") Long eventId, @Bind("userId") Long userId, @Bind("lastUpdated") Timestamp lastUpdated);

    @RegisterMapper(EventMapper.class)
    @SqlQuery("select e.id, e.title, e.description, e.status, e.creator_email, e.video_link, e.location, " +
            "e.start_time, e.end_time, e.created_time, e.last_updated, eu.user_id " +
            "from event_user eu inner join event e on eu.event_id = e.id where user_id = :userId " +
            "order by e.start_time desc")
    List<EventDto> findEventsByUserId(@Bind("userId") Long userId);

    @RegisterMapper(UserMapper.class)
    @SqlQuery("select u.id, u.first_name, u.last_name, u.gender, u.age, u.email " +
            "from cal_user u inner join event_user eu on u.id = eu.user_id where eu.event_id = :eventId")
    List<Cal_User> getUsersFromEvent(@Bind("eventId") Long eventId);

    @SqlQuery("select id from event_user where event_id = :eventId and user_id = :userId")
    Long getEventUserFromUserId(@Bind("eventId") Long eventId, @Bind("userId") Long userId);
}
