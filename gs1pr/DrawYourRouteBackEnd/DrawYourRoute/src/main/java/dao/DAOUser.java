
package dao;

import java.util.List;
import javax.persistence.Query;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;


public class DAOUser extends DAOBase<User>{
    
    public User findByNameAndPassword(String name, String password) {
        List<User> usuarios = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM user WHERE nickName = ? AND password = ?", User.class);
            query.setParameter(1, name);
            query.setParameter(2, password);
            usuarios = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
}
