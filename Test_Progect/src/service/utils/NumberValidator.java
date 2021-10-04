package service.utils;


import java.util.List;

public class NumberValidator {
    public boolean isValid(List<String> number) {
        if (number.size() > 0) {
            for (String num : number) {
                if (!(num.startsWith("375") || num.startsWith("+375"))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
