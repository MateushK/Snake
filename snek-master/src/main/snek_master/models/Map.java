package snek_master.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {

    // enum z informacją jakiego typu mamy pole (puste, bomba, jabłko)
    enum Type{
        EMPTY,
        BOMB,
        APPLE
    }

    private State state;
    private int width;
    private int height;
    private List<Snake> snakes = new ArrayList<>();
    private List<Point> blockedPoints = new ArrayList<>();
    private Type[][] map;
    Point apple = null;
    private int eatedApples = -1;

    // tworzenie modelu mapy o pustych polach
    public Map(int width, int height){
        this.width = width;
        this.height = height;
        map = new Type[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                map[i][j] = Type.EMPTY;
            }
        }
    }

    // zwraca szerokość
    public int getWidth(){
        return width;
    }

    // zwraca wysokość
    public int getHeight(){
        return height;
    }

    public void addSnake(Snake snake){
        snake.setMap(this);
        snakes.add(snake);
    }

    public Snake getSnake(Point point){
        for(Snake snake : snakes){
            if(snake.hasPosition(point)){
                return snake;
            }
        }
        return null;
    }

    // sprawdzamy czy snake istnieje na danym polu
    public boolean hasSnake(Point point){
        if(getSnake(point) == null)
            return false;
        return true;
    }

    public List<Snake> getSnakes(){
        return snakes;
    }

    public void addBlockedPoint(Point point){
        if (map[point.getX()][point.getY()] == Type.EMPTY) {
            map[point.getX()][point.getY()] = Type.BOMB;
            blockedPoints.add(point);
        }
    }

    public List<Point> getBlockedPoints(){
        return blockedPoints;
    }

    // blokada sprawdzająca
    public boolean isBlocked(Point p){
        if(p.getY() >= height || p.getX() >= width || p.getX() < 0 || p.getY() < 0)
            return true;
        return map[p.getX()][p.getY()] == Type.BOMB;
    }

    // sadzenie jabłka
    public void newApple(){
        eatedApples++;
        if (apple != null) {
            map[apple.getX()][apple.getY()] = Type.EMPTY;
            state.increaseScore();
        }

        // lista wolnych pól
        List<Point> freePoints = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!hasSnake(new Point(i, j)) && map[i][j] == Type.EMPTY) {
                    freePoints.add(new Point(i, j));
                }

            }
        }
        // usadowienie jabłka na losowym wolnym polu
        apple = freePoints.get((int) Math.floor(Math.random() * freePoints.size()));
        map[apple.getX()][apple.getY()] = Type.APPLE;
    }

    // zwraca pozycje jabłka
    public Point getApple() {
        return apple;
    }

    // zwraca ilość zjedzonych jabłek
    public int getEatedApples(){
        return eatedApples;
    }

    @Override
    public String toString() {
        return "Map{" +
                "width=" + width +
                ", height=" + height +
                ", snakes=" + snakes.toString() +
                ", blockedPoints=" + blockedPoints +
                ", map=" + Arrays.toString(map) +
                ", apple=" + apple +
                ", eatedApples=" + eatedApples +
                '}';
    }

    // kopia mapy
    public Map copy(){
        Map newMap = new Map(width, height);
        for (Snake snake : snakes) {
            newMap.addSnake(snake.copy());
        }
        for (Point p : blockedPoints) {
            newMap.addBlockedPoint(p);
        }
        return newMap;
    }

    // ustawienie stanu
    public void setState(State state){
        this.state = state;
    }
}
