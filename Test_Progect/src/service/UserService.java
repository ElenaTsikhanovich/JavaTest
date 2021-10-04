package service;

import model.User;
import service.api.IUserService;
import service.utils.DataValidator;
import storage.UserStorage;
import storage.api.IUserStorage;
import java.util.List;

public class UserService implements IUserService {
    private static UserService instance;
    private IUserStorage iUserStorage;
    private DataValidator dataValidator;

    private UserService(){
        this.iUserStorage= UserStorage.getInstance();
        this.dataValidator=DataValidator.getInstance();
    }

    public static UserService getInstance() {
        if(instance==null){
            instance=new UserService();
        }
        return instance;
    }

    @Override
    public boolean save(User user) {
        boolean valid = this.dataValidator.isValid(user);
        if(valid) {
            if (!isExist(user.getEmail())) {
                this.iUserStorage.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(User user, String currentEmail) {
        boolean valid = this.dataValidator.isValid(user);
        if (valid) {
            if (!user.getEmail().equalsIgnoreCase(currentEmail)) {
                if (isExist(user.getEmail())) {
                    return false;
                }
            }
            this.iUserStorage.update(user, currentEmail);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String email) {
        return this.iUserStorage.delete(email);
    }

    @Override
    public User get(String email) {
            return this.iUserStorage.get(email);
    }

    @Override
    public List<User> getAll() {
        return this.iUserStorage.getAll();
    }

    @Override
    public boolean isExist(String email) {
        List<User> all = this.iUserStorage.getAll();
        User user = all.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
        return user != null;
    }
}
