package ru.navu.carsharing.services.user;


import ru.navu.carsharing.models.User;

import javax.mail.MessagingException;

public interface UserService {
    void save(User user) throws MessagingException;
    User findByUsername(String username);
    boolean activateUser(String code);
}
