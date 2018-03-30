package dao;

//import mapper.EventMapper;
import mapper.EventMapper;
import model.Dto.EventDto;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.unstable.BindIn;

import java.sql.Timestamp;
import java.util.List;

//@UseStringTemplate3StatementLocator
public interface EventDAO {
    @SqlQuery("insert into event (gcal_id, title, description, status, creator_email, video_link, location, " +
            "start_time, end_time, created_time, last_updated) " +
            "values (:gcalId, :title, :description, :status, :creatorEmail, :videoLink, :location, :startTime, " +
            ":endTime, :createdTime, :lastUpdated) returning id")
    Long addEvent(@Bind("gcalId") String gcalId, @Bind("title") String title, @Bind("description") String description,
                        @Bind("status") String status, @Bind("creatorEmail") String creatorEmail, @Bind("videoLink") String videoLink,
                        @Bind("location") String location, @Bind("startTime") Timestamp startTime, @Bind("endTime") Timestamp endTime,
                        @Bind("createdTime") Timestamp createdTime, @Bind("lastUpdated") Timestamp lastUpdated);

    @RegisterMapper(EventMapper.class)
    @SqlQuery("select e.id, e.title, e.description, e.status, e.creator_email, e.video_link, e.location, " +
            "e.start_time, e.end_time, e.created_time, e.last_updated " +
            "from event e where e.id = :eventId")
    EventDto getEvent(@Bind("eventId") Long eventId);


    @SqlQuery("select id from event where gcal_id = :gcalId")
    Long getEventByGCalId(@Bind("gcalId") String gcalId);


    @SqlUpdate("update event set title = :title, description = :description, status = :status, video_link = :videoLink, " +
            "location = :location, start_time = :startTime, end_time = :endTime, last_updated = :lastUpdated")
    void updateEvent(@Bind("eventId") Long eventId, @Bind("title") String title, @Bind("description") String description,
                     @Bind("status") String status, @Bind("videoLink") String videoLink, @Bind("location") String location,
                     @Bind("startTime") Timestamp startTime, @Bind("endTime") Timestamp endTime, @Bind("lastUpdated") Timestamp lastUpdated);


//    @SqlQuery("select e.id, e.title, e.description, e.status, e.creator_email, e.video_link, e.location, " +
//            "e.start_time, e.end_time, e.created_time, e.last_updated, eu.user_id from event e " +
//            "inner join event_user eu on eu.event_id = e.id " +
//            "where id in (<eventIds>)")
//    List<EventDto> getEventsByIds(@BindIn("eventIds") List<Long> eventIds);
}
