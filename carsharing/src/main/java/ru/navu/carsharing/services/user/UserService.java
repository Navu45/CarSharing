package ru.navu.carsharing.services.user;


import ru.navu.carsharing.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
