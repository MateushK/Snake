package snek_master.file;

import snek_master.options.Config;

import java.io.*;

public class ServerScoreManager {

    private int score = 0;

    public ServerScoreManager() throws IOException {
        InputStream file = new FileInputStream(Config.scoreFile);

        score = file.read();
        file.close();
    }

    public int getScore() {
        return score;
    }

    public void clear() {
        score = 0;
    }


    public void write() throws IOException {
        OutputStream out = new FileOutputStream(Config.scoreFile);
        out.write(score);
        out.close();
    }

    public void setScore(int score) {
        if (score > this.score)
            this.score = score;
    }
}
