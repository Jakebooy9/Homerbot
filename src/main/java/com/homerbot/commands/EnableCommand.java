package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.Homerbot;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class EnableCommand extends AbstractCommand {

    private Homerbot homerbot;
    public EnableCommand(Homerbot homerbot){
        super("enable");
        this.homerbot = homerbot;
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(isBotDev(user)){
            if(args.length == 0){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Usage: !enable [command]");
                return;
            }
            AbstractCommand c = getCommand(args[0]);
            if(c == null){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + args[0] + "\" not found");
                return;
            }
            if(c.isEnabled()){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + c.getName() + "\" is already enabled");
                return;
            }
            c.setEnabled(true);
            group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + c.getName() + "\" enabled");
            return;
        }else{
            group.sendMessage("<b>" + user.getUsername() + " > </b>Only the bot devs can run this command");
            return;
        }
    }



}
