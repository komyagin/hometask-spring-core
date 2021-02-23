package io.github.komyagin.dao.repository;

import io.github.komyagin.dao.entity.User;
import io.github.komyagin.services.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UsersRepository implements UserService {
    private static long id = 0;
    private static Map<Long, User> usersContainer = new HashMap<>();

    public User getUserByEmail(String email) {
        return usersContainer.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findAny().orElse(null);
    }

    public static Map<Long, User> receiveAllUsers() {
        return usersContainer;
    }

    @Override
    public User save(@Nonnull User user) {
        user.setId(id);
        return usersContainer.put(id++, user);
    }

    @Override
    public void remove(@Nonnull User user) {
        usersContainer.remove(user.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        return usersContainer.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return usersContainer.values();
    }
}
