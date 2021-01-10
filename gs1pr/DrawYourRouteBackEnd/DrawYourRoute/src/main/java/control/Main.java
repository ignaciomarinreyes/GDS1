package control;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.simple.parser.ParseException;

import persistence.HibernateUtil;

public class Main {
    
    public static void main(String[] args) throws ParseException {
        createDataBase();
        HibernateUtil.shutdown();
    }


    private static void createDataBase() {   
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }       
}
