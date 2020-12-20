package servicedrawyourroute.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.Controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Coordinate;
import model.Draw;
import model.Route;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;


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
    @Path("userByNickname/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosByNickName(@PathParam("nickName") String nickName) {
        User user = controller.getUserByNickName(nickName);
        return Response.ok(user).build();
    }
    
    @GET
    @Path("userById/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosByIdUser(@PathParam("idUser") int idUser) {
        User user = controller.getUserById(idUser);
        return Response.ok(user).build();
    }

    @GET
    @Path("friendsByNickname/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendsByNickName(@PathParam("nickName") String nickName) {
        Set<User> friends = controller.getFriendsByNickName(nickName);
        String json = converterJavaToJson.toJson(friends);
        return Response.ok(json).build();
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

    @POST
    @Path("addUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(HashMap<String, Object> request) {
        String name = (String) request.get("name");
        String lastName = (String) request.get("lastName");
        String nickName = (String) request.get("nickName");
        String password = (String) request.get("password");
        String email = (String) request.get("email");
        try {
            controller.addUser(new User(name, lastName, nickName, password, email));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("addFriend")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFriend(HashMap<String, Object> request) {
        String nickNameLoggedUser = (String) request.get("nickNameLoggedUser");
        String nickNameFriend = (String) request.get("nickNameFriend");
        User loggedUser = controller.getUserByNickName(nickNameLoggedUser);
        User friendUser = controller.getUserByNickName(nickNameFriend);
        try {
            controller.addFriend(loggedUser, friendUser);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("addRoute")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRoute(HashMap<String, Object> request) {
        JSONObject jsnobject = new JSONObject(request);      
        String nameRoute = (String) jsnobject.getString("name");
        String dateRoute = (String) jsnobject.getString("date");
        String nickNameLoggedUser = (String) jsnobject.getString("nickNameLoggedUser");
        BigDecimal idDraw = (BigDecimal) jsnobject.getBigDecimal("idDraw");
        JSONArray jsonArray = jsnobject.getJSONArray("coordinates");   
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        Double score = 0.;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objectJSON = jsonArray.getJSONObject(i);
            coordinates.add(new Coordinate(objectJSON.getDouble("lat"), objectJSON.getDouble("lng")));
        }
        try {
            score = controller.addRoute(nameRoute, dateRoute, nickNameLoggedUser, coordinates, idDraw.intValue());
            return Response.ok("{ \"score\": " + score + "}").build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
    
    @POST
    @Path("addDraw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDraw(HashMap<String, Object> request) {
        JSONObject jsnobject = new JSONObject(request);      
        String nameRoute = (String) jsnobject.getString("name");
        String dateRoute = (String) jsnobject.getString("date");
        String nickNameLoggedUser = (String) jsnobject.getString("nickNameLoggedUser");
        JSONArray jsonArray = jsnobject.getJSONArray("coordinates");   
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objectJSON = jsonArray.getJSONObject(i);
            coordinates.add(new Coordinate(objectJSON.getDouble("lat"), objectJSON.getDouble("lng")));
        }
        try {
            controller.addDraw(nameRoute, dateRoute, nickNameLoggedUser, coordinates);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
    
    @GET
    @Path("draw/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawById(@PathParam("id") int id) {
        Draw draw = controller.getDrawById(id);
        String json = converterJavaToJson.toJson(draw);
        return Response.ok(draw).build();
    }
    
    @GET
    @Path("route/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRouteById(@PathParam("id") int id) {
        Route route = controller.getRouteById(id);
        String json = converterJavaToJson.toJson(route);
        return Response.ok(json).build();
    }
    
    @GET
    @Path("draws/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawsByUser(@PathParam("idUser") int idUser) {
        List<Draw> draw = controller.getDrawsByUser(idUser);
        return Response.ok(draw).build();
    }
    
    @GET
    @Path("routes/{idUser}") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoutes(@PathParam("idUser") int idUser) {
        List<Route> route = controller.getRoutesByUser(idUser);
        return Response.ok(route).build();
    }
    
    @DELETE
    @Path("route/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRoute(@PathParam("id") int id) {
        try{
            controller.deleteRouteById(id);
            return Response.ok().build();
        }catch(IllegalStateException e){
            return Response.noContent().build();
        }
    }
    
    @DELETE
    @Path("draw/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDraw(@PathParam("id") int id) {
        try{
            controller.deleteDrawById(id);
            return Response.ok().build();
        }catch(IllegalStateException e){
            return Response.noContent().build();
        }
    }
    
    @POST
    @Path("addLike")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLike(HashMap<String, Object> request) {
        BigDecimal idLoggedUser = (BigDecimal) request.get("idLoggedUser");
        BigDecimal idRoute  =  (BigDecimal) request.get("idRoute");
        User loggedUser = controller.getUserById(idLoggedUser.intValue());
        Route routeToLike = controller.getRouteById(idRoute.intValue());
        try {
            controller.addLike(loggedUser, routeToLike);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
    
    @GET
    @Path("numberLikes/{idRoute}") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberLikes(@PathParam("idRoute") int idRoute) {
        int numberLikes = 0;
        try{
           numberLikes = controller.getNumberLikes(idRoute);
           return Response.ok("{ \"numberLikes\": " + numberLikes + "}").build();
        } catch(NullPointerException e){
           return Response.noContent().build();
        }             
    }
    
    @GET
    @Path("routesMoreLikes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoutesMoreLikes() {
        List<Route> routes = controller.getRoutesMoreLikes();
        return Response.ok(routes).build();
    }
    
    @PUT
    @Path("updatedUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedUser(HashMap<String, Object> request) {
        BigDecimal idUserToUpdate =  (BigDecimal) request.get("idUserToUpdate");
        String name = (String) request.get("name");
        String lastName = (String) request.get("lastName");
        String nickName = (String) request.get("nickName");
        String password = (String) request.get("password");
        String email = (String) request.get("email");
        try {
            controller.updateUser(idUserToUpdate.intValue(), new User(name, lastName, nickName, password, email));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
}
