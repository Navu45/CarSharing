package ru.navu.carsharing.services.security;

public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);

}
