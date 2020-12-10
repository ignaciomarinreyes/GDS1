package servicedrawyourroute.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.Controller;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;

import request.LoginRequest;

@Path("userResource")
public class UserResource {

    private Controller controller;
    private Gson converterJavaToJson;

    public UserResource() {
        GsonBuilder b = new GsonBuilder();
        controller = new Controller();
        converterJavaToJson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    }

    @GET 
    @Path("user/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosByNickName(@PathParam("nickName") String nickName) {
        User user = controller.getUserByNickName(nickName);
        String json = converterJavaToJson.toJson(user);
        return Response.ok(json).build();
    }
    
    @GET 
    @Path("friends/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendsByNickName(@PathParam("nickName") String nickName) {
        Set<User> friends = controller.getFriendsByNickName(nickName);
        Set<String> friendsNickName = new HashSet<String>();
        for (User user : friends) {
            friendsNickName.add(user.getNickName());
        }      
        return Response.ok(friendsNickName).build();
    }

    @POST
    @Path("loggedUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loggedUser(HashMap<String, Object> request) {
        String nickName = (String) request.get("nickName");
        String password = (String) request.get("password");
        User loggedUser = controller.login(nickName, password);
        loggedUser.setPassword("");
        String json = converterJavaToJson.toJson(loggedUser);
        return Response.ok(json).build();
    }
    
    /*
    @POST
    @Path("loggedUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loggedUser(HashMap<String, Object> request) {
        String nickName = (String) request.get("nickName");
        String password = (String) request.get("password");
        User loggedUser = controller.login(nickName, password);
        loggedUser.setPassword("");
        String json = converterJavaToJson.toJson(loggedUser);
        return Response.ok(json).build();
    }*/
}
