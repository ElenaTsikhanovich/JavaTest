package service.utils;

import service.api.IDataValidation;

public class EmailValidator implements IDataValidation {
    @Override
    public boolean isValid(String email) {
        if(email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            return true;
        } return false;
    }
}
