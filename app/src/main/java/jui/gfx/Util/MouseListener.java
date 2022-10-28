package jui.gfx.Util;

import javax.swing.*;

import jui.Global;

import java.awt.*;
import java.awt.event.*;

public class MouseListener {
    public enum MouseButton {
        MOUSE_LEFT,
        MOUSE_MIDDLE,
        MOUSE_RIGHT
    }

    public static final Button[] buttons = new Button[3];

    static {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button();
        }
    }

    private static Point mousePosition;

    public static Point getMouseScreenPosition() {
        return mousePosition;
    }

    public static Point getMouseWindowPosition() {
        Point mousePos = mousePosition;

        if (mousePosition == null)
            return null;

        SwingUtilities.convertPointFromScreen(mousePos, Global.getWindow());
        return mousePos;
    }

    public static void update() {
        for (Button button : buttons) {
            button.pressed = button.down && !button.last;
            button.last = button.down;
        }

        mousePosition = MouseInfo.getPointerInfo().getLocation();
    }

    public static class Button {
        public boolean down, pressed, pressedTick, last;
    }

    public static java.awt.event.MouseListener getListener() {
        return new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                MouseListener.buttons[e.getButton() - 1].down = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseListener.buttons[e.getButton() - 1].down = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        };
    }
}