package com.akkanova.rhythm_game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class BasePanel extends Canvas {
    protected static final Color DEFAULT_BACKGROUND_COLOR = new Color(54, 57, 63);
    protected static final Color DEFAULT_FOREGROUND_COLOR = new Color(187, 187, 187);
    protected long lastRender;
    protected int fps;

    /** Specific timer to maintain target TPS */
    public final Timer renderer;

    protected BasePanel() { this(15); } // 60 FPS
    protected BasePanel(int timerDelay) {
        // Tell AWT not to bother repainting our canvas since we're
        // going to do that ourselves...
        this.setIgnoreRepaint(true);
        this.lastRender = -1;
        this.renderer = new Timer(
            timerDelay,
            (event) -> {
                long currentTime = System.currentTimeMillis();
                long delta = this.lastRender != -1
                    ? currentTime - this.lastRender
                    : 0;

                if (this.shouldRender(delta)) {
                    BufferStrategy strategy = getBufferStrategy();
                    if (strategy == null) {
                        createBufferStrategy(2);
                        strategy = getBufferStrategy();
                    }

                    Graphics2D graphics2D = (Graphics2D) strategy.getDrawGraphics();
                    graphics2D.setColor(DEFAULT_BACKGROUND_COLOR);
                    graphics2D.fillRect(0, 0, getBounds().width, getBounds().height);
                    graphics2D.setColor(DEFAULT_FOREGROUND_COLOR);

                    this.render(graphics2D);

                    graphics2D.dispose();
                    strategy.show();
                }

                this.lastRender = System.currentTimeMillis();
            }
        );

        this.renderer.setRepeats(true);
    }


    /** @return whether the loop should call draw */
    protected abstract boolean shouldRender(long deltaMS); // Logic
    protected abstract void render(Graphics2D graphics2D); // Graphics

    //-----------------------------------------------------------------------------------
    // Graphics Method
    //-----------------------------------------------------------------------------------

    protected void drawFPS(Graphics2D graphics2D) {
        double fps = 1000 / (double) (System.currentTimeMillis() - lastRender);
        String string = String.format("%.1f FPS", fps);
        graphics2D.drawString(string, 2, graphics2D.getFontMetrics().getHeight());
    }
}
