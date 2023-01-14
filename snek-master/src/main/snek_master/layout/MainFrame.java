package snek_master.layout;
import snek_master.options.Config;
import javax.swing.*;

public class MainFrame extends JFrame{

    // ustawiamy główne okno gry
    public MainFrame() {
        setTitle("Snek");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setResizable(false);
        setSize(Config.frameWidth, Config.frameHeight);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
