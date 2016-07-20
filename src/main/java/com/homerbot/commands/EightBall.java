package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.Methods;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class EightBall extends AbstractCommand {

    public EightBall(String name){
        super(name);
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(args.length == 0){
            group.sendMessage("<b>" + user.getUsername() + " > </b>Usage: !8ball [question..]");
            return;
        }
        group.sendMessage("<b>" + user.getUsername() + " > </b>" + Methods.get8ball());
        return;
    }

}
