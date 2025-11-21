package me.mihaif.licenta.common.events.ui;

import javafx.scene.paint.Color;

public class DrawEvent implements UIEvent{
	public DrawEvent(double x1, double y1, double x2, double y2, double thickness, Color colour) {
		this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2; this.thickness = thickness; this.colour = colour;
	}
	public double x1, y1, x2, y2, thickness;
	public Color colour;
}
