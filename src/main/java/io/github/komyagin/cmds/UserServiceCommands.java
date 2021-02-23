package io.github.komyagin.cmds;

import io.github.komyagin.dao.entity.User;
import io.github.komyagin.dao.repository.UsersRepository;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserServiceCommands extends AbstractCommands implements CommandMarker {

    private UsersRepository repository;

    public void setRepository(UsersRepository repository) {
        this.repository = repository;
    }

    @CliAvailabilityIndicator(value = {"add-user", "get-user-by-id",
            "remove-user", "get-user-by-email", "get-users"})
    public boolean isAdminEnabled() {
        return adminEnableExecuted;
    }

    @CliCommand(value = {"add-user", "register-user"}, help = "Adding a user. Mandatory parameters: --firstName --lastName --email")
    public String addUser(
            @CliOption(key = { "firstName" }, mandatory = true, help = "First name of a user") final String firstName,
            @CliOption(key = { "lastName"}, mandatory = true, help = "Last name of a user") final String lastName,
            @CliOption(key = { "email" }, mandatory = true, help = "Email of a user") final String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        repository.save(user);
        return "User added!\n";
    }

    @CliCommand(value = "get-user-by-id", help = "Getting a user by its id number")
    public String getUserById(
            @CliOption(key = "id", mandatory = true, help = "Id number of user") final String id) {
        return repository.getById(Long.valueOf(id)).toString();
    }

    @CliCommand(value = "remove-user", help = "Removing a user by id number")
    public String removeUserById(
            @CliOption(key = "id", help = "Provide users' id number") final String id) {
        User user = repository.getById(Long.valueOf(id));
        if (user != null) {
            repository.remove(user);
            return "User deleted!";
        }
        return "User not found!";
    }

    @CliCommand(value = "get-user-by-email", help = "Removing user by email address")
    public String getUserByEmail(
            @CliOption(key = "email", help = "Provide a user email address") final String email) {
        return Objects.requireNonNull(repository.getUserByEmail(email)).toString();
    }

    @CliCommand(value = "get-users", help = "Get all of the users from repository")
    public String getUsers() {
        StringBuilder buf = new StringBuilder();
        for (User user : repository.getAll()) {
            buf.append(user).append(OsUtils.LINE_SEPARATOR);
        }
        return buf.toString();
    }
}
