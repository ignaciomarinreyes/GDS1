package control;

import dao.DAOUser;
import java.util.Set;
import model.User;

public class Controller {
    
    private DAOUser DAOUser;

    public Controller() {
        this.DAOUser = new DAOUser();
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
}
