package web.service.dao;

import org.springframework.stereotype.Repository;
import web.service.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public void update(User user) {
        em.merge(user);
        em.flush();
    }

    @Override
    public void deleteById(long id) throws NullPointerException{
        User user = findById(id);
        if(user == null){
            throw new NullPointerException();
        }
        em.remove(user);
        em.flush();
    }
}
