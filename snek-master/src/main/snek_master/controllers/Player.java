package snek_master.controllers;

import snek_master.options.Config;
import snek_master.exceptions.SnakeEatHimselfException;
import snek_master.exceptions.SnakeGoToOutOfMapException;
import snek_master.models.Map;
import snek_master.models.Snake;

import javax.swing.*;

public class Player extends Thread {

    long sleepTime;
    Map map;
    JPanel panel;
    private boolean winned = false;
    private int neededApples;

    public Player(JPanel panel, Map map, int neededApples) {
        super();
        this.map = map;
        this.panel = panel;
        this.neededApples = neededApples;
    }

    public void run(){
        while(map.getEatedApples() < neededApples){
            try{
                sleep(Config.sleepTime);
            } catch (InterruptedException e){
                // nothing
            }
            for(Snake snake : map.getSnakes()){
                try{
                    snake.move();
                    if(snake.getPoints().get(0).equals(map.getApple())){
                        snake.grow();
                        map.newApple();
                    }
                } catch(SnakeGoToOutOfMapException | SnakeEatHimselfException e) {
                    System.out.println("F");
                    panel.revalidate();
                    panel.repaint();

                    return;
                }
            }
            panel.revalidate();
            panel.repaint();

        }
        winned = true;
    }

    public boolean isWinned() {
        return winned;
    }
}
