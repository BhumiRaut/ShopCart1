package ShopCart1.ShopCart1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductDao {
    public void save(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        }
    }

    public void update(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        }
    }

    public Product getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    public List<Product> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }
}