package io.github.komyagin.dao.entity.cmds;

import io.github.komyagin.dao.entity.dao.entity.User;
import io.github.komyagin.dao.entity.dao.repository.UsersRepository;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
public class AdminCommands implements CommandMarker {

    private UsersRepository repository = new UsersRepository();

    public void setRepository(UsersRepository repository) {
        this.repository = repository;
    }

    @CliCommand(value = "add-user", help = "Adding a user. Mandatory parameters: --firstName --lastName --email")
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

    @CliCommand(value = "get-users", help = "Get all of the users from repository")
    public String getUsers() {
        StringBuffer buf = new StringBuffer();
        for (User user : repository.getAll()) {
            buf.append(user + OsUtils.LINE_SEPARATOR);
        }
        return buf.toString();
    }
}
