package snek_master.layout;
import snek_master.options.Config;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

    // ustawiamy okno backgroundowe (to, w którym znajduje się mapa gry)
    public MainPanel(){
        setLayout(null);
        setBackground(Color.decode("#578a35"));
        setSize(Config.frameWidth, Config.frameHeight);
    }
}
