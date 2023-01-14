package snek_master.layout;
import snek_master.options.Config;
import snek_master.models.Map;
import snek_master.models.Point;
import snek_master.models.Snake;
import snek_master.utils.Direction;
import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel{
    Map map;

    // ustawiamy okno snake
    public SnakePanel(Map map, int width, int height){
        setBounds(Config.boardMargin, Config.frameHeight - Config.boardHeight-2* Config.boardMargin-20, width, height);
        setBackground(new Color(0, 0, 0, 0));
        this.map = map;
    }

    // rysujemy snake
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int cellWidth = getWidth() / map.getWidth();
        int cellHeight = getHeight() / map.getHeight();

        Graphics2D g2 = (Graphics2D) g;

        //
        for(Snake snake : map.getSnakes()){
            Point prevPoint = null;
            Point sarPoint = null;
            for(Point point : snake.getPoints()){
                int x = point.getX();
                int y = point.getY();

                g2.setColor(snake.getColor());
                int x1 = x * cellWidth, y1 = y * cellHeight, w = cellWidth, h = cellHeight;
                g2.fillRect(x1, y * cellHeight, w, cellHeight);

                if(prevPoint == null){
                    sarPoint = point;
                }

                prevPoint = point;
            }
            {
                int x = sarPoint.getX();
                int y = sarPoint.getY();
                int x1 = x * cellWidth, y1 = y * cellHeight, w = cellWidth, h = cellHeight;

                g2.setColor(Config.snakeEyeColor);
                int snakeEyeRadius = Config.snakeEyeRadius;
                int snakeMouthHeight = Config.snakeMouthHeight;
                int snakeMouthWidth = w;

                if(map.getApple().getDistance(sarPoint) <= Config.bigHeadDistance) {
                    snakeEyeRadius *= Config.bigHeadScale;
                    snakeMouthHeight *= Config.bigHeadScale;
                }
                if (snake.getDirection() == Direction.RIGHT || snake.getDirection() == Direction.LEFT) {
                    g2.fillRoundRect(x1 + w / 2 - snakeEyeRadius, y1 - snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius);
                    g2.fillRoundRect(x1 + w / 2 - snakeEyeRadius, y1 + h - snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius);

                } else{
                    g2.fillRoundRect(x1 - snakeEyeRadius, y1 + h / 2 - snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius);
                    g2.fillRoundRect(x1 + w - snakeEyeRadius, y1 + h / 2 - snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius, 2*snakeEyeRadius);
                }

                g2.setColor(Config.snakeMouthColor);
                if(snake.getDirection() == Direction.LEFT) {
                    g2.fillArc(x1-snakeMouthHeight/2, y1+h/2 - snakeMouthWidth/2, snakeMouthHeight, snakeMouthWidth, 90, 180);
                } else if(snake.getDirection() == Direction.RIGHT) {
                    g2.fillArc(x1+w-snakeMouthHeight/2, y1+h/2 - snakeMouthWidth/2, snakeMouthHeight, snakeMouthWidth, 270, 180);
                }
                else if(snake.getDirection() == Direction.UP) {
                    g2.fillArc(x1+w/2-snakeMouthWidth/2, y1 - snakeMouthHeight/2, snakeMouthWidth, snakeMouthHeight, 0, 180);
                } else {
                    g2.fillArc(x1+w/2-snakeMouthWidth/2, y1+h-snakeMouthHeight/2, snakeMouthWidth, snakeMouthHeight, 180, 180);
                }
            }
        }
    }
}