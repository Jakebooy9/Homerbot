package com.homerbot;

import fr.delthas.skype.Group;
import fr.delthas.skype.User;

public abstract class AbstractCommand extends CommandHandler {

    private String name;

    /* Allow you to enable/disable the command at will. */
    private boolean enabled;

    public AbstractCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public abstract void execute(Group group, User user, String[] args);

}
