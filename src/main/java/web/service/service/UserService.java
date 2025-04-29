package web.service.service;

import web.service.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id);
    void save(User user);
    void update(User user);
    void deleteById(long id);
}