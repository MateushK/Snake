package snek_master.controllers;

import snek_master.options.Config;
import snek_master.file.ScoreManager;
import snek_master.models.*;
import snek_master.models.Snake;
import snek_master.models.State;
import snek_master.utils.Direction;
import snek_master.layout.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameRunner{
    public void run(ScoreManager scoreManager) {
        MainFrame mainFrame = new MainFrame();
        MainPanel mainPanel = new MainPanel();

        mainFrame.setContentPane(mainPanel);

        mainFrame.setVisible(true);

        State state = new State(scoreManager.getScore());
        StatePanel statePanel = new StatePanel(state);
        mainPanel.add(statePanel);
        while(true){

            System.out.println("Snake is running ....  ");
            Map map = new Map(20, 20);
            map.addSnake(new Snake(new Point(10, 10), Direction.RIGHT, Config.snakeColor));
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
            map.newApple();
            map.setState(state);
            Snake snake = map.getSnakes().get(0);

            MapPanel mapPanel = new MapPanel(map, Config.boardWidth, Config.boardHeight);
            mainPanel.add(mapPanel);

            SnakePanel snakePanel = new SnakePanel(map, Config.boardWidth, Config.boardHeight);
            mainPanel.add(snakePanel);

            mainPanel.setComponentZOrder(mapPanel, 1);
            mainPanel.setComponentZOrder(snakePanel, 0);

            int neededApples = 1000000;
            state.setNeededEats(neededApples);

            Player player = new Player(mainPanel, map, neededApples);
            player.start();

            //
            mainFrame.addKeyListener(new KeyListener(){
                @Override
                public void keyTyped(KeyEvent e){
                }

                @Override
                public void keyPressed(KeyEvent e){
                    try{
                        switch (e.getKeyCode()){
                            case 37:
                                snake.setDirection(Direction.LEFT);
                                break;
                            case 38:
                                snake.setDirection(Direction.UP);
                                break;
                            case 39:
                                snake.setDirection(Direction.RIGHT);
                                break;
                            case 40:
                                snake.setDirection(Direction.DOWN);
                                break;
                        }
                        player.interrupt();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });


            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainPanel.remove(snakePanel);
            mainPanel.remove(mapPanel);
            state.resetCurrentEats();
            if(player.isWinned()){
                System.out.println("GOD GAMER");
            } else{
                state.decreaseHealth();
            }
            scoreManager.setScore(state.getMaxScore());
            scoreManager.write();
        }
    }
}
