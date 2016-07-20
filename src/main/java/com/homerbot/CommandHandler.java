package com.homerbot;

import fr.delthas.skype.Group;
import fr.delthas.skype.User;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private static List<AbstractCommand> commands = new ArrayList<>();

    public List<AbstractCommand> getCommands() {
        return commands;
    }

    public String getName(Object o){
        return o.getClass().getName();
    }

    /* Command being the command that was executed
     * Group being the group this command was executed in,
     * User being the user that executed it,
     * and args being well... the arguments.
     */

    public void handle(Group group, User user, String message) {
        if (!message.startsWith("!")) return; // checking if the prefix is at the start
        message = message.substring(1); // this removes the first character of the message, so removing !

        String[] args = message.split(" "); // splitting into arguments
        String command = args[0]; // our command

        AbstractCommand cmd = null;

        for (AbstractCommand c : getCommands()) {
            if (c.getName().equalsIgnoreCase(command)) {
                cmd = c;
                break; // this will stop the loop as we've already found the command
            }
        }

        if (cmd == null) {
            group.sendMessage("<b>" + user.getUsername() + " > </b>Command not found");
            return; // looks a lot cleaner to do it this way
        }

        if (!cmd.isEnabled()) {
            group.sendMessage("<b>" + user.getUsername() + " > </b>Command not enabled.");
            return;
        }

        // here we are removing the first argument, which is the command
        String[] newArgs = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            newArgs[i - 1] = args[i];
        }

        // now we execute the command
        cmd.execute(group, user, newArgs);
    }

    public AbstractCommand getCommand(String name) {
        for (AbstractCommand command : getCommands()) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }


    public void register(AbstractCommand command) {
        if (getCommand(command.getName()) == null) {
            getCommands().add(command);
            command.setEnabled(true);
        }
    }

    public void unregister(AbstractCommand command) {
        if (getCommands() != null) {
            getCommands().remove(command);
            command.setEnabled(false);
        }
    }
}