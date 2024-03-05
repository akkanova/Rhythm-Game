package com.akkanova.rhythm_game.ui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public final MenuPanel menuPanel;
    public final JPanel cards;

    public GameWindow(int width, int height) {
        super("The Rhythm Game");
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center of the screen
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Insets insets = getInsets();
        this.setSize(
            insets.left   + width  + insets.right,
            insets.bottom + height + insets.top
        );

        this.menuPanel = new MenuPanel();
        this.menuPanel.grabFocus();

        // Use CardLayout to allow switching between
        // different panels.
        this.cards = new JPanel(new CardLayout());
        this.cards.add(menuPanel, MenuPanel.CARD_KEY);
        this.getContentPane().add(cards);
    }

    public void setCurrentVisibleCard(String cardKey) {
        CardLayout layout = (CardLayout) this.cards.getLayout();
        layout.show(this.cards, cardKey);
    }

    /** Defaults to 480 x 480 */
    public GameWindow() {
        // TODO UI Scaling
        this(480, 480);
    }
}
