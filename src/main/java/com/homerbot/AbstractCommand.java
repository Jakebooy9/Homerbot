package com.homerbot;

import fr.delthas.skype.Group;
import fr.delthas.skype.Pair;
import fr.delthas.skype.Role;
import fr.delthas.skype.User;

import java.sql.SQLException;
import java.util.List;

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

    public boolean isGroupAdmin(User user, Group group){
        List<Pair<User, Role>> users = group.getUsersWithRoles();
        for(Pair p : users){
            if(user == p.getFirst()){
                Role r = (Role)p.getSecond();
                if(r.toString().equalsIgnoreCase("ADMIN")) return true;
            }
        }
        return false;
    }

    public boolean isBotDev(User user){
        if(user.getUsername().equalsIgnoreCase("tfkjake")
                || user.getUsername().equalsIgnoreCase("nickhalden22")
                || user.getUsername().equalsIgnoreCase("skype.name.321")
                || user.getUsername().equalsIgnoreCase("damadrigames")) return true;
        return false;
    }

    public void mysqlDisconnect() {
        try {
            if (Homerbot.connection != null) {
                Homerbot.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
