package jui.gfx.UIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.geom.Line2D;

public class Gauge extends UIObject {

    public Gauge(Point pos, String unit, double max) {
        super(pos);
        this.m_Unit = unit;
        this.m_MaxValue = max;
    }
    public Gauge(Point pos, String unit, double max, int decimalCount) {
        super(pos);
        this.m_Unit = unit;
        this.m_MaxValue = max;
        this.m_DecimalCount = decimalCount;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Point mid = new Point(((int) this.m_Position.getX() - m_GaugeWidth / 2),
                ((int) this.m_Position.getY() - m_GaugeWidth / 2));
        int dilation = 10;
        double value = (this.m_CurValue >= 1 ? (this.m_CurValue / this.m_MaxValue) : 0) * 9.0;

        double angle = ((2.0 * Math.PI - Math.PI / 2.0) / 9.0) * value + ((3.0 * Math.PI) / 4.0);

        double x = ((m_GaugeWidth / 2.1) * Math.cos(angle)) + this.m_Position.getX(),
                y = ((m_GaugeWidth / 2.1) * Math.sin(angle)) + this.m_Position.getY();

        // draw the gauge outline
        g.setColor(Color.black);
        g.fillOval((int) mid.getX(), (int) mid.getY(), m_GaugeWidth, m_GaugeWidth);

        // draw the gauge inner
        g.setColor(Color.gray);
        g.fillOval((int) mid.getX() + dilation / 2,
                (int) mid.getY() + dilation / 2,
                m_GaugeWidth - dilation, m_GaugeWidth - dilation);

        // draw the current needle value
        g.setColor(Color.red);
        g2.setStroke(new BasicStroke(4));
        g2.draw(new Line2D.Float((int) this.m_Position.getX(), (int) this.m_Position.getY(), (int) x, (int) y));

        // draw the numbers
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times", Font.BOLD, 16));
        for (int i = 0; i <= 9; i++) {
            int curNum = (int) (i * (this.m_MaxValue / 9));
            angle = ((2.0 * Math.PI - Math.PI / 2.0) / 9.0) * i + ((3.0 * Math.PI) / 4.0);

            x = ((m_GaugeWidth / 2.5) * Math.cos(angle)) + this.m_Position.getX();
            y = ((m_GaugeWidth / 2.5) * Math.sin(angle)) + this.m_Position.getY();
            g.drawString("" + curNum, (int) x - 10, (int) y);
        }

        // draw the middle
        g.fillOval((int) this.m_Position.getX() - 5, (int) this.m_Position.getY() - 5, 10, 10);

        
        // draw the current value
        g.drawString(Math.floor(this.m_CurValue * m_DecimalPlace) / m_DecimalPlace + this.m_Unit, (int)this.m_Position.getX() - 30, (int)(this.m_Position.getY() + m_GaugeWidth / 3));
    }

    public void setValue(double val) {
        this.m_CurValue = val;
    }

    public void setMaxValue(double val) {
        this.m_MaxValue = val;
    }

    public void setUnit(String unit) {
        this.m_Unit = unit;
    }

    public void getDecimalCount(int decimalCount) {
        this.m_DecimalCount = decimalCount;
    }

    public double getCurrentValue() {
        return this.m_CurValue;
    }

    public double getMaxValue() {
        return this.m_MaxValue;
    }

    public String getUnit() {
        return this.m_Unit;
    }

    public int getDecimalCount() {
        return this.m_DecimalCount;
    }

    private String m_Unit;
    private double m_CurValue, m_MaxValue;
    private int m_GaugeWidth = 250, m_DecimalCount = 1, m_DecimalPlace = Integer.parseInt("1" + "0".repeat(m_DecimalCount));
}
