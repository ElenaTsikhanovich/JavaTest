package service.utils;

import service.api.IDataValidation;

public class NumberValidator implements IDataValidation {
    @Override
    public boolean isValid(String number) {
        if (number.startsWith("375")||number.startsWith("+375")){
            return true;
        }return false;
    }
}
