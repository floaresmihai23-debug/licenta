package me.mihaif.licenta.ui;

import javafx.scene.canvas.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class DrawingCanvas extends Pane {

    private final Canvas canvas;
    private GraphicsContext gc;

    private double lastX, lastY; 
    private double borderPadding = 3;
    
    
    private Color currentColour = Color.BLACK;
    private double currentThickness = 2;
    
    public Canvas getCanvas() {
    	return canvas;
    }
    
    public void setCurrentColour(Color colour) {
        this.currentColour = colour;
    }	
    public void setCurrentThickness(double thickness) {
        this.currentThickness = thickness;
    }	
    
    public Color getCurrentColour() {
        return currentColour;
    }
    public DrawingCanvas() {
        canvas = new Canvas();
        this.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();


        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.layoutXProperty().bind(this.layoutXProperty().add(borderPadding));
        canvas.layoutYProperty().bind(this.layoutXProperty().add(borderPadding));
	    
        canvas.widthProperty().bind(this.widthProperty().subtract(borderPadding*2));
        canvas.heightProperty().bind(this.heightProperty().subtract(borderPadding*2));

        this.widthProperty().addListener(_ -> redrawBackground());
        this.heightProperty().addListener(_ -> redrawBackground());
        
        enableDrawing();
    }
    
    private void redrawBackground() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    private void enableDrawing() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            lastX = e.getX();
            lastY = e.getY();
            
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            gc.setLineCap(StrokeLineCap.ROUND);   // for line ends
            gc.setLineJoin(StrokeLineJoin.ROUND);
            gc.setStroke(currentColour);
            gc.setLineWidth(currentThickness);
            gc.strokeLine(lastX, lastY, x, y);
            
            //Client.getInstance().sendDraw(lastX, lastY, x, y, currentThickness, currentColour);
            
            lastX = x;
            lastY = y;
        });
    }


}
