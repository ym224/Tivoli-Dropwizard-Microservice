
import dao.EventDAO;
import dao.EventUserDAO;
import dao.NoteDAO;
import dao.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.session.SessionHandler;
import org.skife.jdbi.v2.DBI;
import resource.*;
import service.GoogleCalendarService;

import javax.ws.rs.client.Client;


public class TivoliApplication extends Application<TivoliConfiguration> {


    public static void main(String[] args) throws Exception {
        new TivoliApplication().run(args);
    }

    private static void enableSessionSupport(Environment env) {
        env.servlets().setSessionHandler(new SessionHandler());
    }

    @Override
    public void initialize(Bootstrap<TivoliConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TivoliConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TivoliConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(TivoliConfiguration config,
                    Environment environment) {
        final Client client = new JerseyClientBuilder(environment).using(config.getJerseyClientConfiguration())
                .build(getName());

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");

        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final NoteDAO noteDAO = jdbi.onDemand(NoteDAO.class);
        final EventDAO eventDAO = jdbi.onDemand(EventDAO.class);
        final EventUserDAO eventUserDAO = jdbi.onDemand(EventUserDAO.class);

        environment.jersey().register(new CalendarResource(eventDAO, userDAO, eventUserDAO, new GoogleCalendarService()));
        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new EventResource(eventDAO, userDAO, noteDAO, eventUserDAO));
        environment.jersey().register(new UserEventResource(noteDAO, eventUserDAO));
        environment.jersey().register(new NoteResource(eventDAO, noteDAO));
    }
}
