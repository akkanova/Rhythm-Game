package com.akkanova.rhythm_game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {
    /** It's key representation in its parent window CardLayout */
    public static final String CARD_KEY = "menu-panel";
    private GameWindow parent;

    public MenuPanel() {
        super(true); // Enable double-buffered
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
