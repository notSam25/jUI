package jui.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jui.gfx.UIElements.*;
import jui.gfx.Util.MouseListener;

import java.util.Vector;

public class Window extends JFrame {
    private class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (UIObject object : getRenderQueue()) {
                object.render(g);
            }
            g.dispose();
        }

        public Vector<UIObject> getRenderQueue() {
            return m_RenderQueue;
        }

        public void addToRenderQueue(UIObject object) {
            this.m_RenderQueue.add(object);
        }

        public boolean removeFromRenderQueue(UIObject object) {
            return this.m_RenderQueue.remove(object);
        }

        private Vector<UIObject> m_RenderQueue = new Vector<UIObject>();
    }

    public void render() throws InterruptedException {
        m_Panel.setDoubleBuffered(true);
        m_Panel.setPreferredSize(new Dimension(400, 400));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(new Point(75, 200));
        this.setUndecorated(false);
        this.add(this.m_Panel);
        this.pack();
        this.setVisible(true);
        this.addMouseListener(MouseListener.getListener());

        int max = 2400;
        Gauge tGauge = new Gauge(new Point(200, 200), "mph", max, 0);
        this.m_Panel.addToRenderQueue(tGauge);

        while (true) {
            MouseListener.update();
            this.repaint();
            if (tGauge.getCurrentValue() >= max)
                tGauge.setValue(0);
            tGauge.setValue(tGauge.getCurrentValue() + 1);
            Thread.sleep(10);
        }

    }

    private static final String m_WindowName = "javaUI";
    private final Panel m_Panel = new Panel();
}