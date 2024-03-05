package com.akkanova.rhythm_game;

import com.akkanova.rhythm_game.ui.GameWindow;

import java.awt.*;

public final class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(GameWindow::new);
    }
}