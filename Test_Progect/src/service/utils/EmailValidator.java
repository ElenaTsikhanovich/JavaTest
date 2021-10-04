package service.utils;

public class EmailValidator{
    public boolean isValid(String email) {
        if(email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            return true;
        } return false;
    }
}
