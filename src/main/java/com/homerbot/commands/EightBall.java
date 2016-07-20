package com.homerbot.commands;

import com.homerbot.AbstractCommand;
import com.homerbot.Homerbot;
import fr.delthas.skype.Group;
import fr.delthas.skype.User;

import java.util.Random;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class EightBall extends AbstractCommand {

    private Homerbot homerbot;
    public EightBall(Homerbot homerbot){
        super("8ball");
        this.homerbot = homerbot;
    }

    @Override
    public void execute(Group group, User user, String[] args){
        if(args.length == 0){
            group.sendMessage("<b>" + user.getUsername() + " > </b>Usage: !8ball [question..]");
            return;
        }
        group.sendMessage("<b>" + user.getUsername() + " > </b>" + get8ball());
        return;
    }

    public static String get8ball(){
        Random r = new Random();
        int x = r.nextInt(20-1) + 1;
        switch(x){
            case 1:
                return "It is certain.";
            case 2:
                return "It is decidedly so.";

            case 3:
                return "Without a doubt.";

            case 4:
                return "You may rely on it.";

            case 5:
                return "As I see it, yes.";

            case 6:
                return "Most likely.";

            case 7:
                return "Outlook good.";

            case 8:
                return "Yes, definitely.";

            case 9:
                return "Signs point to yes.";

            case 10:
                return "Reply hazy, try again.";

            case 11:
                return "Ask again later.";

            case 12:
                return "Better not tell you now.";

            case 13:
                return "Cannot predict now.";

            case 14:
                return "Concentrate and ask again";

            case 15:
                return "Don't count on it.";

            case 16:
                return "My reply is no.";

            case 17:
                return "My sources say no.";

            case 18:
                return "Outlook not so good.";

            case 19:
                return "Very doubtful.";

            case 20:
                return "Yes";

        }
        return "";
    }

}
