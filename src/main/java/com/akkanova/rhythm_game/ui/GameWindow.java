package com.akkanova.rhythm_game.ui;

import com.akkanova.rhythm_game.common.GlobalConfig;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public final MenuPanel menuPanel;
    public BasePanel currentPanel;

    public GameWindow(int width, int height) {
        super("The Rhythm Game");
        this.menuPanel = new MenuPanel();
        this.menuPanel.setBounds(0, 0, width, height);

        // Use CardLayout to allow switching between
        // different panels.
        JPanel pane = (JPanel) this.getContentPane();
        pane.setPreferredSize(new Dimension(width, height));
        pane.setLayout(new CardLayout());
        pane.add(this.menuPanel, this.menuPanel.getName());

        this.setCurrentVisiblePanel(this.menuPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center of the screen
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    public void setCurrentVisiblePanel(BasePanel panel) {
        if (currentPanel != null)
            currentPanel.renderer.stop();

        CardLayout layout = (CardLayout) this.getContentPane().getLayout();
        layout.show(this.getContentPane(), panel.getName());
        panel.requestFocus();

        this.currentPanel = panel;
        this.currentPanel.renderer.start();
    }

    /** Defaults to 640 x 360 (16:9) */
    public GameWindow() {
        this(
            GlobalConfig.getInstance().getWindowWidth(),
            GlobalConfig.getInstance().getWindowHeight()
        );
    }
}
