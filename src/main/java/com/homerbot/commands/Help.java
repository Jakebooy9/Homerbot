package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.CommandHandler;
import com.homerbot.Homerbot;
import com.sun.deploy.util.StringUtils;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class Help extends AbstractCommand {

    private Homerbot homerbot;
    public Help(Homerbot homerbot){
        super("help");
        this.homerbot = homerbot;
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(args.length == 0) {
            int total = getCommands().size();
            System.out.println("Commands: " + getCommands());
            List<String> s = new ArrayList<>();
            for (AbstractCommand c : getCommands()) {
                if (c.isEnabled()) {
                    s.add(c.getName());
                }
            }
            String cmds = StringUtils.join(s, ", ");
            group.sendMessage("<b>" + user.getUsername() + " > </b>Commands ( " + total + " ): " + cmds);
            return;
        }else if(args[0].equalsIgnoreCase("disabled")){
            ArrayList<AbstractCommand> cmds = new ArrayList<>();
            List<String> s = new ArrayList<>();
            for(AbstractCommand c : getCommands()){
                if(!c.isEnabled()){
                    cmds.add(c);
                    s.add(c.getName());
                }
            }
            int total = cmds.size();
            String cmdS = StringUtils.join(s, ", ");
            group.sendMessage("<b>" + user.getUsername() + " > </b>Disabled Commands ( " + total + " ): " + cmdS);
        }
    }

}
