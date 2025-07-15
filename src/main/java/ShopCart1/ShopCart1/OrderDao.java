package ShopCart1.ShopCart1;

//package ShopCart1.ShopCart1;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDao {
    public void save(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(order);
            tx.commit();
        }
    }
}