package ru.navu.carsharing.services.user;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navu.carsharing.models.Role;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.repositories.UserRepository;
import ru.navu.carsharing.services.EmailSender;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private EmailSender sender;

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null)
        {
            user.setActive(true);
            return true;
        }
        return false;
    }

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, EmailSender sender) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.sender = sender;
    }

    @Override
    public void save(User user) throws MessagingException {
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(Role.USER);
        user.setRoles(rolesSet);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        String message = String.format(
                "Hello, dear User!\nWelcome to CarSharing. Please, visit this link: " +
                        "<a href=\"http:/localhost:8080/activate/%s\">Activate your account</a>",
                user.getActivationCode()
        );
        sender.send(user.getUsername(), "Activate your account. CarSharing App", message);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
