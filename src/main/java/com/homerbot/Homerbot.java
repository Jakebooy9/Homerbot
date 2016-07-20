package com.homerbot;

import com.homerbot.commands.*;
import fr.delthas.skype.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakebooy on 19/07/2016.
 */
public class Homerbot extends CommandHandler{

    public List<AbstractCommand> commands = new ArrayList<>();

    public Connection connection = null;
    Statement statement = null;

    public static Skype skype;

    public Homerbot(){

        mysqlConnect("root", "", "localhost", "3306", "homerbot");

        register(new Help(this));
        register(new DisableCommand(this));
        register(new EnableCommand(this));
        register(new EightBall(this));
        register(new Stop(this));

        createSkype();

        String[] cred = getSkypeCredentials();
        skype = new Skype(cred[0], cred[1]);

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
            handle(group, us, message);
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

    public static void main(String args[]){
        new Homerbot();
    }

    public void createSkype(){
        File file = new File("skype.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            return;
        }
    }

    public String[] getSkypeCredentials(){
        File file = new File("skype.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return line.split(":");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void mysqlConnect(String username, String password, String host, String port, String database){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void mysqlDisconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
