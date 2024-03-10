package com.akkanova.rhythm_game.common;

import java.io.*;
import java.util.Objects;

public class GlobalConfig implements Serializable {
    //-----------------------------------------------------------------------------------
    // Java Thread-safe Singleton lazy loading
    //-----------------------------------------------------------------------------------

    private static class GlobalConfigHolder {
        private static final GlobalConfig INSTANCE = new GlobalConfig();
    }

    public static GlobalConfig getInstance() {
        return GlobalConfigHolder.INSTANCE;
    }

    //-----------------------------------------------------------------------------------
    // Deserialization and Serialization
    //-----------------------------------------------------------------------------------

    private static final long SerialVersionUID = 1L;
    private static final String CONFIG_FILE_NAME = "config.ser";

    private GlobalConfig() {
        try (
            InputStream fileStream = Objects.requireNonNull(
                getClass()
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_FILE_NAME));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileStream)
        ) {
            // Copy the contents of the saved config to this current instance
            GlobalConfig savedConfig = (GlobalConfig) objectInputStream.readObject();
            this.windowHeight = savedConfig.getWindowHeight();
            this.windowWidth = savedConfig.getWindowWidth();
            this.showFPS = savedConfig.shouldShowFPS();

            // Assume it's a broken config
        } catch (IOException | ClassNotFoundException | NullPointerException ignored) {
            this.windowHeight = 360;
            this.windowWidth = 640;
            this.showFPS = true;
        }
    }

    public void save() throws IOException {
        String outputDir = Objects.requireNonNull(getClass().getResource("/")).getFile();
        String outputPath = outputDir.concat(File.separator).concat(CONFIG_FILE_NAME);

        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(this);

        fileOutputStream.close();
        objectOutputStream.close();
    }

    private int windowHeight;
    private int windowWidth;
    private boolean showFPS;

    //-----------------------------------------------------------------------------------
    // Setters
    //-----------------------------------------------------------------------------------

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setShowFPS(boolean showFPS) {
        this.showFPS = showFPS;
    }

    //-----------------------------------------------------------------------------------
    // Getters
    //-----------------------------------------------------------------------------------

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public boolean shouldShowFPS() {
        return showFPS;
    }
}
