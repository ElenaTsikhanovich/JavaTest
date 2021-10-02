package service.api;

import model.User;

import java.util.List;

public interface IUserService {
    boolean save(User user);
    boolean update(User user, String email);
    boolean delete(String email);
    User get(String email);
    List<User> getAll();
    boolean isExist(String email);
}
