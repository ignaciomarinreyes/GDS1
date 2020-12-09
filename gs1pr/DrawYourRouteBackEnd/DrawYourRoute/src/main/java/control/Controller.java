package control;

import dao.DAOUser;
import model.User;

public class Controller {
    
    private DAOUser DAOUser;

    public Controller() {
        this.DAOUser = new DAOUser();
    }
   
    public User login(String user, String password){
        return DAOUser.findByNickNameAndPassword(user, password);
    }
}
