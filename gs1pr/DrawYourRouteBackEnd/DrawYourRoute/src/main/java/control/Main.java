package control;

import dao.DAOUser;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import persistence.HibernateUtil;

public class Main {
    
    public static void main(String[] args) {
        //createDataBase();
        DAOUser userDAO = new DAOUser();
        //userDAO.create(new User("ignacio", "marín", "iki", "prueba123", "ignacio@"));
        //System.out.println(userDAO.read(1));
        //userDAO.update(new User(1, "pedro", "marín", "iki", "prueba", "ignacio@"));
        //userDAO.remove(2);
        Controller controller = new Controller();
        System.out.println(controller.login("iki", "prueba"));
        System.out.println(controller.login("iki", "nada"));
        HibernateUtil.shutdown();
    }

    private static void createDataBase() {   
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }       
}
