package servicedrawyourroute.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.Controller;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;

import request.LoginRequest;

@Path("userResource")
public class UserResource {
    
    private Controller controller;
    private Gson converterJavaToJson;
    
    public UserResource(){
        GsonBuilder b = new GsonBuilder();
        controller = new Controller();
        converterJavaToJson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    }
     
    @POST
    @Path("loggedUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loggedUser(LoginRequest request){
        User loggedUser = controller.login(request.nickName, request.password);
        String json = converterJavaToJson.toJson(loggedUser);
        return Response.ok(json).build();
    }
}
