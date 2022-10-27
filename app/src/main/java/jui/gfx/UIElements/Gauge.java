package jui.gfx.UIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Gauge extends UIObject {

    public Gauge(Point pos) {
        super(pos);
    }

    @Override
    public void render(Graphics g) {
        Point mid = new Point(((int) this.m_Position.getX() - width / 2),
                ((int) this.m_Position.getY() - width / 2));
        g.setColor(Color.black);

        g.fillOval((int) mid.getX(), (int) mid.getY(), width, width);

        g.setColor(Color.gray);

        int dilation = 10;

        g.fillOval((int) mid.getX() + dilation / 2,
                (int) mid.getY() + dilation / 2,
                width - dilation, width - dilation);

        double value = (this.m_CurValue >= 1 ? (this.m_CurValue / this.m_MaxValue) : 1) * 9.0;

        double angle = ((2.0 * Math.PI - Math.PI / 2.0) / 9.0) * value + ((3.0 * Math.PI) / 4.0);

        double x = ((width / 2.0) * Math.cos(angle)) + this.m_Position.getX(),
                y = ((width / 2.0) * Math.sin(angle)) + this.m_Position.getY();

        g.setColor(Color.red);
        for (int i = -2; i <= 2; i++)
            g.drawLine((int) this.m_Position.getX() + i, (int) this.m_Position.getY() + i, (int) x + i, (int) y);
        
        g.setColor(Color.black);
        g.fillOval((int)this.m_Position.getX() - 5, (int)this.m_Position.getY() - 5, 10, 10);
    }
    public double m_CurValue = 1, m_MaxValue = 1;
    private int width = 250;
}
