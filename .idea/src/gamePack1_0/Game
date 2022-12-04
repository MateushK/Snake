package gamePack1_0;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener{
    private static final long serialVersionUID = -7887612267521882048L;


    // Liczba milisekund, które wątek aktualizujący snake'a śpi
    private static final int SPEED = 150;

    // Liczba milisekund, które wątki repaint i input śpią
    private static final int REFRESH = 10;

    // Wymiary (wysokość i szerokość) kwadratów, z których składa się plansza (w pikselach)
    private static final int SIZE = 40;

    // Padding (w pikselach) rozróżniający elementy gry
    private static final int PADDING = 0;

    // Flaga do sprawdzenia stanu gry
    private boolean isRunning;

    // Kierunek ruchu snake'a
    // 0, góra
    // 1, dół
    // 2, lewo
    // 3, prawo
    // -1, idle
    private int direction;

    // ArrayList'a przechowująca współrzędne x, y obiektów Point2D, dla wszystkich segmentów snake'a
    private ArrayList<Point2D> snake;

    // ArrayList'a dla wszystkich klawiszy wciśniętych w danym czasie
    private ArrayList<Integer> keys;

    // Obiekt Point2D przechowujący współrzędne x, y dla pożywienia
    private Point2D food;

    // Konstruktor dla obiektu Game.
    // Ustawiamy JPanel
    // Inicjalizujemy wszystkie listy, zmienne i startujemy wszystkie wątki
    public Game(JFrame frame){
        // Superkonstruktor dla komponentu JPanel
        super();

        // Inicjalizacja ArrayList'y klawiszy
        this.setKeys(new ArrayList<Integer>());

        // Ustawiamy rozmiar komponentów, ustawiamy na focusable, dodajemy keyListener
        this.setSize(frame.getWidth() - frame.getInsets().left - frame.getInsets().right, frame.getHeight() - frame.getInsets().top - frame.getInsets().bottom);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        // metoda reset
        this.reset();

        // ustawiamy stan gry na running
        this.setRunning(true);

        // start wątków
        new Thread(new Update()).start();
        new Thread(new Input()).start();
        new Thread(new Repaint()).start();

    }

    @Override
    public void paint(Graphics g) {
        // tworzymy obiekt Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // tworzymy tło gry
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // tworzymy snake'a
        g2d.setColor(Color.GREEN);
        for (Point2D point : snake) {
            g2d.fillRoundRect((int) point.getX() * SIZE + PADDING, (int) point.getY() * SIZE + PADDING, SIZE - PADDING, SIZE - PADDING, 20, 20);
        }

        // tworzymy pożywienie
        g2d.setColor(Color.RED);
        g2d.fillRoundRect((int) food.getX() * SIZE + PADDING, (int) food.getY() * SIZE + PADDING, SIZE - PADDING, SIZE - PADDING, 50, 50);
    }

    // Reset dla parametrów gry
    private void reset() {
        // Ustawiamy snake'a aby był w stanie idle
        setDirection(-1);

        // Resetujemy snake'a do pseudo-losowego miejsca na siatce
        setSnake(new ArrayList<Point2D>());
        getSnake().add(new Point((int) (Math.random() * getWidth() / SIZE), (int) (Math.random() * getHeight() / SIZE)));

        // resetujemy współrzędne pożywienia
        setFood(new Point());
        createFood();
    }



    // Reset pozycji pożywienia
    private void createFood() {
        // zmienne tymczasowe współrzędnych
        int x;
        int y;
        boolean flag;

        do {
            // reset flagi
            flag = false;

            // przypisujemy losowe współrzędne do tymczasowych zmiennych
            x = (int) (Math.random() * getWidth() / SIZE);
            y = (int) (Math.random() * getHeight() / SIZE);

            // pętla for sprawdzająca czy jakikolwiek segment snake'a nakłada się na koordynaty tymczasowe
            for (Point2D point : snake)
                if (point.distance(x, y) == 0)
                    flag = true;

            // pętla trwa dopóki tymczasowe koordynaty się nakładają
        } while (flag);

        // Ustawia pożywienie na tymczasowe koordynaty
        this.getFood().setLocation(x, y);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (!keys.contains(e.getKeyCode()))
            keys.add((Integer) e.getKeyCode());
    }


    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove((Integer) e.getKeyCode());
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (keys.contains(KeyEvent.VK_ESCAPE))
            System.exit(0);
    }

    // gettery/settery
    public ArrayList<Point2D> getSnake() {
        return snake;
    }

    public void setSnake(ArrayList<Point2D> snake) {
        this.snake = snake;
    }

    public Point2D getFood() {
        return food;
    }

    public void setFood(Point2D food) {
        this.food = food;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public ArrayList<Integer> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Integer> keys) {
        this.keys = keys;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private class Update extends Thread {

        @Override
        public void run() {
            // pętla dopóki gra jest odtwarzana
            while (isRunning) {

                // inicjalizujemy "stare" zmienne, które przechowują ostatnie koordynaty, które zostały zmienione, zaczyna od głowy snake'a
                int oldX = (int) snake.get(0).getX();
                int oldY = (int) snake.get(0).getY();


                // przesuwa głowe snake'a w stronę sprecyzowaną przez stare zmienne
                if (direction == 0)
                    snake.get(0).setLocation(oldX, oldY - 1);
                else if (direction == 1)
                    snake.get(0).setLocation(oldX, oldY + 1);
                else if (direction == 2)
                    snake.get(0).setLocation(oldX - 1, oldY);
                else if (direction == 3)
                    snake.get(0).setLocation(oldX + 1, oldY);

                // przesuwa wszystkie podążające segmenty do ich następnej lokalizacji
                for (int i = 1; i < snake.size(); i++) {

                    // ustawimy tymczasowe zmienne na koordynaty segmentu modyfikowanego
                    int tempX = (int) snake.get(i).getX();
                    int tempY = (int) snake.get(i).getY();

                    // zmienia lokalizacje segmentu do starych koordynatów
                    snake.get(i).setLocation(oldX, oldY);

                    // ustawiamy stare zmienne na zmienne tymczasowe
                    oldX = tempX;
                    oldY = tempY;
                }

                // jezeli snake wjedzie w ściane, gra się resetuje
                if (snake.get(0).getX() < 0 || snake.get(0).getY() < 0 || snake.get(0).getX() >= getWidth() / SIZE || snake.get(0).getY() >= getHeight() / SIZE)
                    reset();


                // jezeli snake wjedzie w samego siebie, gra się resetuje
                for (int i = 1; i < snake.size(); i++) {
                    if (snake.get(i).distance(snake.get(0)) == 0) {
                        reset();
                        break;
                    }
                }


                // jeżeli snake wjedzie w pożywienie, dodajemy do niego segment i resetujemy pozycje pożywienia
                if (snake.get(0).distance(food) == 0) {
                    snake.add(new Point(oldX, oldY));
                    createFood();
                }

                // pętla się iteruje co podaną ilość (SPEED) milisekund
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class Input extends Thread {

        @Override
        public void run() {
            // pętla dopóki gra jest odtwarzana
            while (isRunning) {

                // jeżeli ArrayList'a keys nie jest pusta: sprawdzamy które klawisze są wciskane i ustawiamy kierunek na tej podstawie
                if (!keys.isEmpty())
                    if (keys.get(keys.size() - 1) == KeyEvent.VK_W && (direction != 1 || snake.size() == 1))
                        direction = 0;
                    else if (keys.get(keys.size() - 1) == KeyEvent.VK_S && (direction != 0 || snake.size() == 1))
                        direction = 1;
                    else if (keys.get(keys.size() - 1) == KeyEvent.VK_A && (direction != 3 || snake.size() == 1))
                        direction = 2;
                    else if (keys.get(keys.size() - 1) == KeyEvent.VK_D && (direction != 2 || snake.size() == 1))
                        direction = 3;

                // pętla się iteruje co podaną ilość (REFRESH) milisekund
                try {
                    Thread.sleep(REFRESH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class Repaint extends Thread {

        @Override
        public void run() {
            // pętla dopóki gra jest odtwarzana
            while (isRunning) {
                // wywołujemy metodę repaint
                repaint();

                // pętla się iteruje co podaną ilość (REFRESH) milisekund
                try {
                    Thread.sleep(REFRESH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
