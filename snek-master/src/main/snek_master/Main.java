package snek_master;

import snek_master.controllers.GameClient;
import snek_master.controllers.GameRunner;
import snek_master.file.ScoreManager;

import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        GameClient gameClient = new GameClient();
        gameClient.start();


        ScoreManager scoreManager = new ScoreManager(gameClient);

        GameRunner runner = new GameRunner();
        runner.run(scoreManager);

    }
}


        /*mapManager.clear();
        //System.out.println(mapManager);
        {
            Map map = new Map(20, 20);
            map.addSnake(new Snake(new Point(10, 10), Direction.LEFT, GameConfiguration.snakeColor));
            mapManager.addMap(map);
        }
        {
            Map map = new Map(20, 20);
            map.addSnake(new Snake(new Point(10, 10), Direction.RIGHT, GameConfiguration.snakeColor));
            for(int i = 0; i < 5; i++) {
                int x = (int)(20*Math.random());
                int y = (int)(20*Math.random());
                if(x != 10 || y != 10) {
                    map.addBlockedPoint(new Point(x, y));
                }
                else {
                    i--;
                }
            }
            mapManager.addMap(map);
        }
        {
            Map map = new Map(20, 20);
            map.addSnake(new Snake(new Point(10, 10), Direction.RIGHT, GameConfiguration.snakeColor));
            for(int i = 0; i < 10; i++) {
                int x = (int)(20*Math.random());
                int y = (int)(20*Math.random());
                if(x != 10 || y != 10) {
                    map.addBlockedPoint(new Point(x, y));
                }
                else {
                    i--;
                }
            }
            mapManager.addMap(map);
        }
        {
            Map map = new Map(10, 10);
            map.addSnake(new Snake(new Point(4, 4), Direction.RIGHT, GameConfiguration.snakeColor));
            for(int i = 0; i < 3; i++) {
                int x = (int)(10*Math.random());
                int y = (int)(10*Math.random());
                if(x != 4 || y != 4) {
                    map.addBlockedPoint(new Point(x, y));
                }
                else {
                    i--;
                }
            }
            mapManager.addMap(map);
        }
        {
            Map map = new Map(10, 10);
            map.addSnake(new Snake(new Point(4, 4), Direction.RIGHT, GameConfiguration.snakeColor));
            for(int i = 0; i < 6; i++) {
                int x = (int)(10*Math.random());
                int y = (int)(10*Math.random());
                if(x != 4 || y != 4) {
                    map.addBlockedPoint(new Point(x, y));
                }
                else {
                    i--;
                }
            }
            mapManager.addMap(map);
        }
        {
            Map map = new Map(10, 10);
            map.addSnake(new Snake(new Point(4, 4), Direction.RIGHT, GameConfiguration.snakeColor));
            for(int i = 0; i < 13; i++) {
                int x = (int)(10*Math.random());
                int y = (int)(10*Math.random());
                if(x != 4 || y != 4) {
                    map.addBlockedPoint(new Point(x, y));
                }
                else {
                    i--;
                }
            }
            mapManager.addMap(map);
        }
        {
            Map map = new Map(20, 20);
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (i % 4 == 0 && j % 4 == 0) {
                        map.addBlockedPoint(new Point(i, j));
                    }
                }
            }

            map.addSnake(new Snake(new Point(1, 1), Direction.RIGHT, GameConfiguration.snakeColor));

            mapManager.addMap(map);
        }
        /*{
            Map map = new Map(50, 50);
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        map.addBlockedPoint(new Point(i, j));
                    }
                }
            }

            map.addSnake(new Snake(new Point(1, 1), Direction.UP, GameConfiguration.snakeColor));

            mapManager.addMap(map);
        }*/
//mapManager.write();

