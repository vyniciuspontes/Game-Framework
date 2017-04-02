package com.vpontes.gameframework.core;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

abstract public class Game {

    private final Canvas canvas;
    private Screen screen;
    private final int screenWidth;
    private final int screenHeight;
    private float deltaTime;
    private int fps;
    private long a, b;

    /**
     * Creates an instance of Game
     * @param screenWidth
     * @param screenHeight
     * @param windowTitle
     */
    public Game(int screenWidth, int screenHeight, String windowTitle) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        this.canvas = new Canvas(gc, screenWidth, screenHeight, windowTitle);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    
    protected abstract Screen getFirstScreen();

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getFps() {
        return fps;
    }

    public void addKeyListener(KeyListener listener) {
        this.canvas.addKeyListener(listener);
    }

    public void addMouseListener(MouseListener listener) {
        this.canvas.addMouseListener(listener);
    }
    
    public void setScreen(Screen screen){
        this.screen = screen;
    }

    protected void run() {
        float startTime = System.nanoTime();
        screen = getFirstScreen();
        if (screen == null) {
            System.out.println("Screen must be setted. Call \"setScreen(Screen screen)\" method");
        }
        while (true) {

            deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            //Update
            screen.update(deltaTime);
            //Draw
            screen.draw(canvas.getGraphics());

            this.canvas.swapBuffers();

            // FPS
            a = System.nanoTime();
            fps++;

            if (a - b >= 1000000000) {
                fps = 0;
                b = a;
            }

            try {
                Thread.sleep(14);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
