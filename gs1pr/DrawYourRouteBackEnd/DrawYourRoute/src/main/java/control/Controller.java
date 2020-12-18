package control;

import dao.DAOCoordinate;
import dao.DAODraw;
import dao.DAORoute;
import dao.DAOUser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coordinate;
import model.Draw;
import model.Route;
import model.User;
import org.hibernate.internal.CoordinatingEntityNameResolver;

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

    public void addRoute(String nameRoute, String dateRoute, String nickNameLoggedUser, List<Coordinate> coordinates, int idDraw) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateRoute);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }    
        User LoggedUser = DAOUser.findByNickName(nickNameLoggedUser);
        Draw draw = DAODraw.read(idDraw);
        Route route = new Route(nameRoute, date, 100.0);
        for(Coordinate coordinate: coordinates){
            coordinate.setRoute(route);
        }
        route.setCoordinates(coordinates);
        route.setDraw(draw);
        route.setUser(LoggedUser);
        DAORoute.create(route);
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
    
}
