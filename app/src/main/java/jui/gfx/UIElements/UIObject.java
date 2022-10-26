package jui.gfx.UIElements;

import java.awt.Graphics;
import java.awt.Point;

public abstract class UIObject {
    UIObject(Point position) {
        this.m_Position = position;
    }
    public abstract void render(Graphics g);

    protected Point m_Position;
}
