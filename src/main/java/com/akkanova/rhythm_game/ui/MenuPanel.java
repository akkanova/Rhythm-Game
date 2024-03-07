package com.akkanova.rhythm_game.ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends BasePanel {
    public MenuPanel() {
        super();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
            }
        });
    }

    @Override
    public void render(Graphics2D graphics2D) {
        drawFPS(graphics2D);
    }

    @Override
    public boolean shouldRender(long deltaMS) {
        return true;
    }
}
