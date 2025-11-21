package me.mihaif.licenta.ui;

import javafx.scene.paint.Color;
import java.io.Serializable;


public class DrawData implements Serializable {
    private static final long serialVersionUID = 1L;

    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double lineWidth;
    private double red, green, blue, alpha;

    public DrawData(double startX, double startY, double endX, double endY, double lineWidth, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.lineWidth = lineWidth;
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getOpacity();
    }

    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
    public double getLineWidth() { return lineWidth; }
    public Color getColor() { return new Color(red, green, blue, alpha); }
}
