package gamePack1_0;

import javax.swing.*;

public class Frame extends JFrame{

    private static final long serialVersionUID = 786754356768767564;

    public Frame(int width, int height) {
        // Superkonstruktor dla komponentu JFrame, ustawiamy tytuł ramki
        super("Snake");

        // ustawiamy Frame'a
        this.setResizable(false);

        this.pack();
        this.setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
        this.setPreferredSize(getSize());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.pack();

        this.setLocationRelativeTo(null);

        // tworzymy nowy obiekt game, dodaje go do Frame'e
        this.add(new Game(this));
        this.setVisible(true);
    }
}
