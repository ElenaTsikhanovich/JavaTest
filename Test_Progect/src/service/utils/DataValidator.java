package service.utils;

import model.User;

public class DataValidator {
    private static DataValidator instance=new DataValidator();

    public static DataValidator getInstance() {
        return instance;
    }

    public boolean isValid(User user){
        boolean validMail = new EmailValidator().isValid(user.getEmail());
        boolean validNumber = new NumberValidator().isValid(user.getNumber());
        boolean validRole = new RoleValidator().isValid(user.getRole());
        return validMail && validNumber && validRole;
    }
}
