package com.homerbot;

import fr.delthas.skype.Group;
import fr.delthas.skype.Pair;
import fr.delthas.skype.Role;
import fr.delthas.skype.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.homerbot.CommandHandler.getCommands;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class Methods {


    public static void mysqlConnect(String username, String password, String host, String port, String database){
        try {
            Main.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void mysqlDisconnect(){
        try {
            if(Main.connection!=null) {
                Main.connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean isGroupAdmin(User user, Group group){
        List<Pair<User, Role>> users = group.getUsersWithRoles();
        for(Pair p : users){
            if(user == p.getFirst()){
                Role r = (Role)p.getSecond();
                if(r.toString().equalsIgnoreCase("ADMIN")) return true;
            }
        }
        return false;
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

    public static boolean isBotDev(User user){
        if(user.getUsername().equalsIgnoreCase("tfkjake")
                || user.getUsername().equalsIgnoreCase("nickhalden22")
                || user.getUsername().equalsIgnoreCase("skype.name.321")
                || user.getUsername().equalsIgnoreCase("damadrigames")) return true;
        return false;
    }


}
