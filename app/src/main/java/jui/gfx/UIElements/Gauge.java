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

        g.fillOval((int)mid.getX(), (int)mid.getY(), width, width);

        g.setColor(Color.gray);

        int dilation = 10;

        g.fillOval((int)mid.getX() + dilation / 2,
        (int)mid.getY() + dilation / 2,
                width - dilation, width - dilation);

            for(int i = 1; i <= 9; i++) {
                int curNum = (int)Math.round(i * (maxNumber / 9.0));
                // https://danceswithcode.net/engineeringnotes/rotations_in_2d/rotations_in_2d.html
                // https://www.linearmotiontips.com/motion-basics-difference-between-cartesian-and-polar-coordinate-systems/
                // https://keisan.casio.com/exec/system/1223527679
            }
    }

    private int width = 250, maxNumber = 127;
}
