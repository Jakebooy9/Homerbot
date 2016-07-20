package com.homerbot;

import com.homerbot.commands.*;
import fr.delthas.skype.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class Main extends CommandHandler{

    public static Connection connection = null;
    public static Statement statement = null;

    public static Skype skype;

    public static void main(String args[]){

        Main main = new Main();

        Methods.mysqlConnect("root", "", "localhost", "3306", "homerbot");

        main.register(new Help("help"));
        main.register(new DisableCommand("disable"));
        main.register(new EnableCommand("enable"));
        main.register(new EightBall("8ball"));
        main.register(new Stop("stop"));

        skype = new Skype("nickhalden22", "8m4E]msnDPT&C<sY");
        try{
            skype.connect();
            if(skype.isConnected()){
                System.out.println("Connected.");
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }

        skype.setErrorListener(Exception::printStackTrace);


        /* Bot Command Register */
        skype.addGroupMessageListener((group, us, message) -> {
            main.handle(group, us, message);
        });

        /* Group Chat Event Listeners */
        skype.addGroupPropertiesListener(new GroupPropertiesListener() {
            @Override
            public void usersAdded(Group group, List<User> users) {
                for(User u : users){
                    if(u.getUsername().equalsIgnoreCase("nickhalden22")){
                        // do mysql shit for group info
                    }
                }
            }

            @Override
            public void usersRemoved(Group group, List<User> users) {

            }

            @Override
            public void topicChanged(Group group, String topic) {

            }

            @Override
            public void usersRolesChanged(Group group, List<Pair<User, Role>> newRoles) {

            }
        });

    }

}
