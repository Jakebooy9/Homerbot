package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.Main;
import com.homerbot.Methods;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;

/**
 * Created by Jakebooy on 20/07/2016.
 */
public class Stop extends AbstractCommand{

    public Stop(String name){
        super(name);
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(user.getUsername().equalsIgnoreCase("tfkjake")){
            group.sendMessage("Goodbye!");
            Main.skype.disconnect();
            Methods.mysqlDisconnect();
            System.exit(0);
        }else{
            group.sendMessage("<b>" + user.getUsername() + " > </b>Only the bot owner can run this command");
            return;
        }
    }

}
