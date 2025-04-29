package web.service.dao;

import web.service.user.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(long id);
    void save(User user);
    void update(User user);
    void deleteById(long id);
}

