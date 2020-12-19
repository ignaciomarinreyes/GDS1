
package dao;

import java.util.List;
import javax.persistence.Query;
import model.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

public class DAORoute extends DAOBase<Route>{

    public List<Route> findByIdUser(int idUser) {
        List<Route> routes = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM route WHERE id_user = ?", Route.class);
            query.setParameter(1, idUser);
            routes = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return routes;
    }
    
    public int getNumberLikesByIdRoute(int idRoute) {   
        Route route = read(idRoute);       
        return route.getLikes().size();
    }   

    public List<Route> getDraws() {
        List<Route> routes = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM route", Route.class);
            routes = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return routes;
    }
}
