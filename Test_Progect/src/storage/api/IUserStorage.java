package storage.api;

import model.User;

import java.util.List;

public interface IUserStorage {
    void save(User user);
    void update(User user, String email);
    boolean delete(String email);
    User get(String email);
    List<User> getAll();
}
