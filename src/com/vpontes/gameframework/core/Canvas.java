package com.vpontes.gameframework.core;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

public class Canvas {

    private final JFrame frame;
    private final BufferStrategy bs;

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    public void addMouseListener(MouseListener listener) {
        frame.addMouseListener(listener);
    }

    Canvas(GraphicsConfiguration gc, int screenWidth, int screenHeight, String title) {
        frame = new JFrame(gc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setTitle(title);

        // avoid potential deadlock in 1.4.1_02
        try {
            EventQueue.invokeAndWait(() -> {
                frame.createBufferStrategy(2);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
        }

        this.bs = frame.getBufferStrategy();
    }

    public Graphics getGraphics() {
        if (bs == null) {
            return null;
        }
        return this.bs.getDrawGraphics();
    }

    public void swapBuffers() {
        this.bs.show();
        getGraphics().dispose();
        Toolkit.getDefaultToolkit().sync();
    }
}
