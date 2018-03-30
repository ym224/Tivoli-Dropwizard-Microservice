package dao;

import mapper.UserMapper;
import model.Cal_User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("insert into cal_user (first_name, last_name, gender, age, email) " +
            "values (:firstName, :lastName, :gender, :age, :email) returning id")
    Long addUser(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("gender") String gender,
                 @Bind("age") Integer age, @Bind("email") String email);

    @SqlQuery("select id, first_name, last_name, gender, age, email " +
            "from cal_user where id = :id")
    Cal_User findUserById(@Bind("id") long id);

    @SqlQuery("select id " +
            "from cal_user where email = :email")
    Long findUserByEmail(@Bind("email") String email);


    @SqlQuery("select id, first_name, last_name, gender, age, email from cal_user")
    List<Cal_User> getAllUsers();
}
