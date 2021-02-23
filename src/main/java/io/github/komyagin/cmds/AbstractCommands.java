package io.github.komyagin.cmds;

import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class AbstractCommands {
    protected boolean adminEnableExecuted = false;

    @CliCommand(value = "admin-enable")
    public String adminEnable() {
        adminEnableExecuted = true;
        return "Admin commands enabled.";
    }
}
