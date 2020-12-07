package control;

import dao.DAOUser;

public class Controller {
    
    private DAOUser DAOUser;

    public Controller() {
        this.DAOUser = new DAOUser();
    }
   
    public boolean login(String user, String password){
        return DAOUser.findByNameAndPassword(user, password) != null;
    }
}
