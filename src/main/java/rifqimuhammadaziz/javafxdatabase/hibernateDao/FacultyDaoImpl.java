package rifqimuhammadaziz.javafxdatabase.hibernateDao;

import org.hibernate.Session;
import rifqimuhammadaziz.javafxdatabase.hibernateEntity.Faculty;
import rifqimuhammadaziz.javafxdatabase.util.DaoService;
import rifqimuhammadaziz.javafxdatabase.util.HibernateDaoService;
import rifqimuhammadaziz.javafxdatabase.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FacultyDaoImpl implements HibernateDaoService<Faculty> {

    @Override
    public int addData(Faculty object) {
        return 0;
    }

    @Override
    public int deleteData(Faculty object) {
        return 0;
    }

    @Override
    public int updateData(Faculty object) {
        return 0;
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
