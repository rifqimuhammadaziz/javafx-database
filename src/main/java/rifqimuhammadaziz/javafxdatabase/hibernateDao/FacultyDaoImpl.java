package rifqimuhammadaziz.javafxdatabase.hibernateDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rifqimuhammadaziz.javafxdatabase.hibernateEntity.Faculty;
import rifqimuhammadaziz.javafxdatabase.util.HibernateDaoService;
import rifqimuhammadaziz.javafxdatabase.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FacultyDaoImpl implements HibernateDaoService<Faculty> {

    @Override
    public int addData(Faculty object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(object);
            transaction.commit();
            result = 1 ;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(Faculty object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            transaction.commit();
            result = 1 ;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int updateData(Faculty object) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            transaction.commit();
            result = 1 ;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public List<Faculty> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> criteriaQuery = builder.createQuery(Faculty.class);
        criteriaQuery.from(Faculty.class);
        List<Faculty> faculties = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return faculties;
    }
}
