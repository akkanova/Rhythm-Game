package com.akkanova.rhythm_game.ui;

import java.awt.*;

public class MenuPanel extends BasePanel {
    public MenuPanel() {
        super();
//        this.setFocusable(true);
//        this.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyChar());
//            }
//        });
    }

    @Override
    public void render(Graphics2D graphics2D, long deltaMS) {
        // Draw Background
        int width =  (int) this.getBounds().getWidth();
        int height = (int) this.getBounds().getHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int radius = (int) Math.abs(Math.sin(System.currentTimeMillis() * 0.001) * (centerX / 2.0));
        int diameter = 2 * radius;

        graphics2D.drawOval(centerX - radius, centerY - radius, diameter, diameter);

        // Draw Foreground
    }
}

