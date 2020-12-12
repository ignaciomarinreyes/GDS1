package control;

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
import model.Route;
import model.User;

public class Controller {
    
    private DAOUser DAOUser;
    private DAORoute DAORoute;

    public Controller() {
        this.DAOUser = new DAOUser();
        this.DAORoute = new DAORoute();
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

    public void addRoute(String nameRoute, String dateRoute, User loggedUser, Coordinate[] coordinates) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateRoute);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        List<Coordinate> coordinateList = Arrays.asList(coordinates);
        DAORoute.create(new Route(nameRoute, date, loggedUser, coordinateList));
    }
}
