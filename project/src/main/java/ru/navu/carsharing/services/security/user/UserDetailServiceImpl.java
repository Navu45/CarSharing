package ru.navu.carsharing.services.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository repository;

    public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("User not found.");
        return user;
    }
}
