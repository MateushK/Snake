package snek_master;

import snek_master.controllers.GameServer;
import snek_master.file.ServerScoreManager;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {

        // uruchomienie serwera
        ServerScoreManager scoreManager = new ServerScoreManager();
        GameServer gameServer = new GameServer();
        gameServer.run(scoreManager);
    }

}



