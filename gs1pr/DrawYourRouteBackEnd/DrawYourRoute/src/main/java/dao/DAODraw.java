package dao;

import java.util.List;
import javax.persistence.Query;
import model.Draw;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

public class DAODraw extends DAOBase<Draw>{

    public List<Draw> findByIdUser(int idUser) {
        List<Draw> draws = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM draw WHERE id_user = ?", Draw.class);
            query.setParameter(1, idUser);
            draws = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return draws;
    }
    
    public List<Draw> getAllDraws() {
        List<Draw> draws = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM draw", Draw.class);
            draws = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return draws;
    }
    
}
