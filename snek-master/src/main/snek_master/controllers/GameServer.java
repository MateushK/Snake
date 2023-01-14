package snek_master.controllers;

import snek_master.options.Config;
import snek_master.file.ServerScoreManager;
import snek_master.models.Map;
import snek_master.models.Point;
import snek_master.models.Snake;
import snek_master.utils.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class GameServer {
    public void run(ServerScoreManager scoreManager) {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(Config.serverPort);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + Config.serverPort + " or listening for a connection");
            System.out.println(e.getMessage());
        }

        while(true){
            try{
                clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                new Thread(() -> {
                    try {
                        String inputLine, outputLine;

                        while ((inputLine = in.readLine()) != null) {
                            System.out.println(inputLine);
                            if (inputLine.equals("exit")) {
                                out.println("bye!");
                                break;
                            } else if (inputLine.equals("getMaxScore")) {
                                out.println(scoreManager.getScore());
                            } else if (inputLine.startsWith("setMaxScore ")) {
                                int score = Integer.parseInt(inputLine.substring(12));
                                scoreManager.setScore(score);
                                scoreManager.write();
                                out.println("newScore " + scoreManager.getScore());
                            } else {
                                out.println("unknownCommand");
                            }

                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + Config.serverPort + " or listening for a connection");
                System.out.println(e.getMessage());

            }
        }
    }
}
