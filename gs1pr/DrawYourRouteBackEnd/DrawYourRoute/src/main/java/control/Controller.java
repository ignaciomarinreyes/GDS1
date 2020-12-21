package control;

import dao.DAOCoordinate;
import dao.DAODraw;
import dao.DAORoute;
import dao.DAOUser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import model.Coordinate;
import model.Draw;
import model.Route;
import model.User;

public class Controller {
    
    private DAOUser DAOUser;
    private DAORoute DAORoute;
    private DAODraw DAODraw;
    private DAOCoordinate DAOCoordinate;

    public Controller() {
        this.DAOUser = new DAOUser();
        this.DAORoute = new DAORoute();
        this.DAODraw = new DAODraw();
        this.DAOCoordinate = new DAOCoordinate();
    }
   
    public User login(String user, String password){
        return DAOUser.findByNickNameAndPassword(user, password);
    }
    
    public User getUserByNickName(String nickName){
        return DAOUser.findByNickName(nickName);
    }
    
    public Set<User> getFriendsByNickName(String nickName){
        User user = getUserByNickName(nickName);
        return user.getFriends();
    }

    public void addUser(User user) {
        DAOUser.create(user);
    }

    public void addFriend(User loggedUser, User friendUser) {
        DAOUser.insertFriend(loggedUser, friendUser);
    }

    public double addRoute(String nameRoute, String dateRoute, String nickNameLoggedUser, ArrayList<Coordinate> coordinates, int idDraw) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateRoute);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }    
        User LoggedUser = DAOUser.findByNickName(nickNameLoggedUser);
        Draw draw = DAODraw.read(idDraw);
        double score = Utils.calculateAccuracy(draw.getCoordinates(), coordinates);
        Route route = new Route(nameRoute, date, score);
        for(Coordinate coordinate: coordinates){
            coordinate.setRoute(route);
        }
        route.setCoordinates(coordinates);
        route.setDraw(draw);
        route.setUser(LoggedUser);
        DAORoute.create(route);
        return score;
    }
    
    public Draw getDrawById(int idDraw) {
        return DAODraw.read(idDraw);
    }

    public void addDraw(String nameRoute, String dateRoute, String nickNameLoggedUser, List<Coordinate> coordinates) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateRoute);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }    
        User loggedUser = DAOUser.findByNickName(nickNameLoggedUser);
        Draw draw = new Draw(nameRoute, date, loggedUser, coordinates);
        for(Coordinate coordinate: coordinates){
            coordinate.setDraw(draw);
        }
        DAODraw.create(draw);
    }

    public List<Draw> getDrawsByUser(int idUser) {
        return DAODraw.findByIdUser(idUser);
    }

    public List<Route> getRoutesByUser(int idUser) {
        return DAORoute.findByIdUser(idUser);
    }

    public void deleteRouteById(int id) throws IllegalStateException {
        DAORoute.remove(id);
    }

    public void deleteDrawById(int id) {
        DAODraw.remove(id);
    }

    public User getUserById(int idUser) {
        return DAOUser.read(idUser);
    }

    public Route getRouteById(int idRoute) {
        return DAORoute.read(idRoute);
    }

    public void addLike(User loggedUser, Route routeToLike) {
        DAOUser.insertLike(loggedUser, routeToLike);
    }

    public int getNumberLikes(int idRoute) throws NullPointerException{
        return DAORoute.getNumberLikesByIdRoute(idRoute);
    }

    public List<Route> getRoutesMoreLikes() {
        List<Route> routes = DAORoute.getDraws();
        Collections.sort(routes, new CompararRoutesLikes());
        if(routes.size() > 10){
            return routes.subList(0, 9);
        }else{
            return routes;
        }
    }

    public void updateUser(int idUserToUpdate, User user) {
       DAOUser.updateUser(idUserToUpdate, user); 
    }
    
    private class CompararRoutesLikes implements Comparator<Route>{       
        @Override
        public int compare(Route r1, Route r2) {
            return new Integer(r2.getLikes().size()).compareTo(new Integer(r1.getLikes().size()));
        }	    
    }
    
    public List<Draw> getDraws() {
        return DAODraw.getAllDraws();
    }
    
    public boolean isUserLikeRoute(int idRoute, int idUser) {
        Route route = DAORoute.read(idRoute);
        Set<User> likes = route.getLikes();
        for (User userGiveLikeToRoute : likes) {
            if(userGiveLikeToRoute.getId() == idUser){
                return true;
            }
        }
        return false;
    }

}
