package storage;

import model.User;
import storage.api.IUserStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage implements IUserStorage {
    private static final String FILE_NAME="users.txt";
    private static UserStorage instance=new UserStorage();
    private List<User> allUsers=new ArrayList<>();

    private UserStorage(){
        readUsersFromFile();
    }

    public static UserStorage getInstance() {
        return instance;
    }

    private void saveUsersInFile(){
        try(FileOutputStream fileOutputStream=new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(allUsers);
        }catch (IOException e){
            throw new IllegalStateException("Ошибка сохранения! Попроуйте еще раз или заходите позже!");
        }
    }

    private void readUsersFromFile(){
        try(FileInputStream fileInputStream=new FileInputStream(FILE_NAME);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)) {
            allUsers=(List<User>) objectInputStream.readObject();
        }catch (IOException|ClassNotFoundException exception){
            throw new IllegalStateException("Ошибка чтения! Попробуйте еще раз или зайдите позже!");
        }finally {
            saveUsersInFile();
            return;
        }
    }

    @Override
    public void save(User user) {
      readUsersFromFile();
      allUsers.add(user);
      saveUsersInFile();
    }

    @Override
    public void update(User user, String email) {
        User user1 = get(email);
        allUsers.remove(user1);
        allUsers.add(user);
        saveUsersInFile();
    }

    @Override
    public boolean delete(String email) {
        User user = get(email);
        if(user!=null) {
            allUsers.remove(user);
            saveUsersInFile();
            return true;
        }else return false;
    }

    @Override
    public User get(String email) {
        readUsersFromFile();
        return allUsers.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        readUsersFromFile();
        return allUsers;
    }

}
