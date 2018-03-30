package resource;

import dao.UserDAO;
import model.Cal_User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @Path("/all")
    public List<Cal_User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Cal_User getUser(@PathParam("id") Long userId){
       return userDAO.findUserById(userId);
    }

}