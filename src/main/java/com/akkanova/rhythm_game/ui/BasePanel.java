package com.akkanova.rhythm_game.ui;

import com.akkanova.rhythm_game.common.GlobalConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Objects;

public abstract class BasePanel extends Canvas {
    protected static final Color DEFAULT_BACKGROUND_COLOR = new Color(54, 57, 63);
    protected static final Color DEFAULT_FOREGROUND_COLOR = new Color(187, 187, 187);
    protected long lastRender;

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

                BufferStrategy strategy = getBufferStrategy();
                if (strategy == null) {
                    createBufferStrategy(2);
                    strategy = Objects.requireNonNull(getBufferStrategy());
                }

                Graphics2D graphics2D = (Graphics2D) strategy.getDrawGraphics();
                graphics2D.setColor(DEFAULT_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, getBounds().width, getBounds().height);
                graphics2D.setColor(DEFAULT_FOREGROUND_COLOR);

                if (GlobalConfig.getInstance().shouldShowFPS()) {
                    String string = String.format("%.1f FPS", 1000 / (double) delta);
                    graphics2D.drawString(string, 2, graphics2D.getFontMetrics().getHeight());
                }

                this.render(graphics2D, delta);

                graphics2D.dispose();
                strategy.show();
                Toolkit.getDefaultToolkit().sync();

                this.lastRender = System.currentTimeMillis();
            }
        );

        this.renderer.setRepeats(true);
    }

    protected abstract void render(Graphics2D graphics2D, long deltaMS); // Graphics

    //-----------------------------------------------------------------------------------
    // Graphics Helper Methods
    //-----------------------------------------------------------------------------------
}
