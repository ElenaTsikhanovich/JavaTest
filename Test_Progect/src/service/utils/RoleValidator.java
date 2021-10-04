package service.utils;

import model.ERole;

import java.util.List;

public class RoleValidator {
    public boolean isValid(List<ERole> roles){
        return roles.size() != 0;
    }
}
