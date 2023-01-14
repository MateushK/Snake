package snek_master.options;

import java.awt.*;

// dane konfiguracyjne dla projektu snake'a
public class Config{

    // ustawienie szerokości, wysokości i marginesu
    public static int frameWidth = 600;
    public static int frameHeight = 700;
    public static int boardWidth = 560;
    public static int boardHeight = 560;
    public static int boardMargin;
    static {boardMargin = Math.min(frameWidth-boardWidth, frameHeight-boardHeight)/2;}


    // ustawienie cech snake'a
    public static Color snakeColor = new Color(73, 122, 235);
    public static int snakeEyeRadius = 5;
    public static Color snakeEyeColor = Color.decode("#f7f7f7");

    public static int snakeMouthHeight = 10;
    public static Color snakeMouthColor = new Color(255, 0, 0);
    public static int bigHeadDistance = 5;
    public static double bigHeadScale = 2;
    public static long sleepTime = 200;
    public static int initialHealth = 2;

    public static int serverPort = 12999;

    // ścieżki plików lokalnych
    public static String mapsFile = "src/data/maps.dat";
    public static String scoreFile = "src/data/score.dat";
}
