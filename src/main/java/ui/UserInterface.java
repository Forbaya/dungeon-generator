package ui;

import gen.Dungeon;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.Dimension;

public class UserInterface implements Runnable {
    private JFrame frame;
    private Dungeon dungeon;

    public UserInterface() {
        int cellCount = 10;
        dungeon = new Dungeon(cellCount);
        dungeon.generateDungeon();
    }

    @Override
    public void run() {
        frame = new JFrame("Dungeon generator");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
    }
}
