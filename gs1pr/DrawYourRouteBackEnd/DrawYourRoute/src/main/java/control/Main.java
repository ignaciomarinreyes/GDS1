package control;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import model.Route;
import model.User;
import persistence.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        nativeQuery();
        //create();
        //read();
        //update();
        //remove();
        //createRoute();
        //findByNombreUser("Paco");
    }

    private static void findByNombreUser(String nombre) {
        List<User> usuarios = null;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM user WHERE nombre = ?", User.class);
            query.setParameter(1, nombre);
            usuarios = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        for (User user : usuarios) {
            System.out.println(user.toString());
            System.out.println(user.getRoutes());
        }
    }

    private static void createRoute() {
        Transaction transaction = null;
        Route route = new Route("Paseo por el mar", new User(2, "Paco"));
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(route);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(route.toString());
    }

    private static void remove() {
        Transaction transaction = null;
        User user = new User(1);
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void update() {
        Transaction transaction = null;
        User user = read();
        user.setNombre("Pedro");
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(user.toString());
    }

    private static User read() {
        Transaction transaction = null;
        User user = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, 1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(user.toString());
        return user;
    }

    private static void create() {
        Transaction transaction = null;
        User user = new User("Paco");
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(user.toString());
    }

    private static void nativeQuery() {
        List<User> usuarios = null;
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            usuarios = session.createNativeQuery("SELECT * FROM user", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        for (User user : usuarios) {
            System.out.println(user.toString());
        }
    }
}
