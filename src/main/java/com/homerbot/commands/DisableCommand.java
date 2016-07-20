package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.Homerbot;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;


/**
 * Created by Jakebooy on 19/07/2016.
 */
public class DisableCommand extends AbstractCommand{

    public DisableCommand(String name){
        super(name);
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(isBotDev(user)){
            if(args.length == 0){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Usage: !disable [command]");
                return;
            }
            AbstractCommand c = getCommand(args[0]);
            if(c == null){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + args[0] + "\" not found");
                return;
            }
            if(c.getName().equalsIgnoreCase("disable") || c.getName().equalsIgnoreCase("help") || c.getName().equalsIgnoreCase("enable")){
                group.sendMessage("<b>" + user.getUsername() + " > </b>You can't disable that command");
                return;
            }
            if(!c.isEnabled()){
                group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + c.getName() + "\" is already disabled");
                return;
            }
            c.setEnabled(false);
            group.sendMessage("<b>" + user.getUsername() + " > </b>Command \"" + c.getName() + "\" disabled");
            return;
        }else{
            group.sendMessage("<b>" + user.getUsername() + " > </b>Only the bot devs can run this command");
            return;
        }
    }

}
