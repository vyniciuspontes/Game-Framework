package com.vpontes.gameframework.core;

import java.awt.Graphics;

public abstract class Screen {

    protected Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void draw(Graphics g);
}
