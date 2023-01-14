package snek_master.file;
import snek_master.controllers.GameClient;
public class ScoreManager{

    private int score = 0;
    private GameClient gameClient;

    public ScoreManager(GameClient gameClient){
        this.gameClient = gameClient;

        score = Integer.parseInt(gameClient.request("getMaxScore"));

    }

    public int getScore(){
        return score;
    }

    public void clear(){
        score = 0;
    }

    public void write(){
        gameClient.request("setMaxScore "+score);
    }

    public void setScore(int score){
        this.score = score;
    }
}
