package snek_master.models;

import snek_master.options.Config;

public class State {
    private int health;
    private int level;
    private int maxScore;
    private int score;
    private int currentEats;
    private int neededEats;

    public State(int maxScore){
        this.health = Config.initialHealth;
        level = 0;
        this.maxScore = maxScore;
        currentEats = score = 0;
    }

    // funkcja dekrementująca życie <3
    public synchronized void decreaseHealth() {
        health--;
        currentEats = 0;
        // jeżeli życia spadną poniżej zera następuje reset
        if(health < 0) {
            health = Config.initialHealth;
            level = 0;
            score = 0;
        }
    }

    // zwiększenie
    public synchronized void increaseScore() {
        score++;
        currentEats++;
        if(score > maxScore)
            maxScore = score;
    }

    // funkcja zwracająca ilość żyć
    public synchronized int getHealth(){
        return health;
    }

    // funkcja zwracająca najwyższy lokalny wynik
    public synchronized int getMaxScore(){
        return maxScore;
    }

    // funkcja zwracająca obecny wynik
    public synchronized int getScore(){
        return score;
    }

    // reset zjedzonych jabłek
    public void resetCurrentEats(){
        currentEats = 0;
    }

    public void setNeededEats(int neededEats){
        this.neededEats = neededEats;
    }

    public int getCurrentEats() {
        return currentEats;
    }

    public int getNeededEats() {
        return neededEats;
    }
}
