package me.mihaif.licenta.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColourPallete extends Pane{
	private static Color[] colours = {
		    // Light variants
		    new Color(1, 0.8, 0.9, 1),   // light pink
		    new Color(0.6, 0.8, 1, 1),   // light blue
		    new Color(0.6, 1, 0.6, 1),   // light green
		    new Color(1, 1, 0.6, 1),     // light yellow
		    new Color(1, 0.6, 1, 1),     // light magenta
		    new Color(0.6, 1, 1, 1),     // light cyan
		    new Color(0.8, 0.8, 0.8, 1), // light gray
		    new Color(0.8, 0.6, 0.5, 1), // light brown
		    new Color(1, 0.9, 0.8, 1),   // light peach
		    new Color(0.9, 0.8, 1, 1),   // light lavender

		    // Normal / mid variants
		    new Color(1, 0, 0, 1),       // red
		    new Color(0, 0, 1, 1),       // blue
		    new Color(0, 1, 0, 1),       // green
		    new Color(1, 1, 0, 1),       // yellow
		    new Color(1, 0, 1, 1),       // magenta
		    new Color(0, 1, 1, 1),       // cyan
		    new Color(0.5, 0.5, 0.5, 1), // gray
		    new Color(0.6, 0.3, 0, 1),   // brown
		    new Color(1, 0.6, 0.6, 1),   // peach
		    new Color(0.8, 0.8, 1, 1),   // lavender

		    // Dark variants
		    new Color(0.5, 0, 0, 1),     // dark red
		    new Color(0, 0, 0.5, 1),     // dark blue
		    new Color(0, 0.5, 0, 1),     // dark green
		    new Color(1, 0.6, 0, 1),     // dark yellow/orange
		    new Color(0.5, 0, 0.5, 1),   // dark magenta/purple
		    new Color(0, 0.5, 0.5, 1),   // dark cyan/teal
		    new Color(0.2, 0.2, 0.2, 1), // dark gray
		    new Color(0.4, 0.2, 0, 1),   // dark brown
		    new Color(0.7, 0.3, 0.3, 1), // dark peach/salmon
		    new Color(0.5, 0.5, 0.8, 1), // dark lavender
		};
	private List<Rectangle> colourNodes = new ArrayList<>();
	private DrawingCanvas canvas;
	public DrawingCanvas getLinkedCanvas() {
		return canvas;
	}
	public ColourPallete(DrawingCanvas canvas) {
		this.canvas = canvas;
		addColourNodes();
	}
	private void addColourNodes() {
        int n = colours.length;
        double gapPercent = 0.02;        // horizontal gap between squares
        double verticalGapPercent = 0.02; // vertical gap between rows
        double minWidthPercent = 0.1;    // minimum width to trigger multi-row

        // Determine max squares per row based on minWidthPercent
        int squaresPerRow = Math.min(n, (int)Math.floor(1.0 / minWidthPercent));
        int rows = (int)Math.ceil((double)n / squaresPerRow);

        // Horizontal slot width fraction
        double totalGapH = gapPercent * (squaresPerRow - 1);
        double slotWidthPercent = (1.0 - totalGapH) / squaresPerRow;

        // Vertical slot height fraction
        double totalGapV = verticalGapPercent * (rows - 1);
        double slotHeightPercent = (1.0 - totalGapV) / rows;

        for (int i = 0; i < n; i++) {
            Rectangle square = new Rectangle();
            Color colour = colours[i];
            square.setFill(colour);
            square.setStroke(Color.BLACK);
            square.setStrokeWidth(1);

            int row = i / squaresPerRow;
            int col = i % squaresPerRow;
            
            
            square.setOnMouseClicked(_ -> canvas.setCurrentColour(colour));
            
            // Bind layoutX
            double layoutXPercent = col * (slotWidthPercent + gapPercent);
            square.layoutXProperty().bind(this.widthProperty().multiply(layoutXPercent));

            // Bind layoutY
            double layoutYPercent = row * (slotHeightPercent + verticalGapPercent);
            square.layoutYProperty().bind(
            	    this.heightProperty().multiply(layoutYPercent)
            	        .add(
            	            Bindings.divide(
            	                this.heightProperty().multiply(slotHeightPercent)
            	                    .subtract(
            	                        Bindings.min(
            	                            square.widthProperty(),
            	                            this.heightProperty().multiply(slotHeightPercent)
            	                        )
            	                    ),
            	                2
            	            )
            	        )
            	);

            // Bind width
            square.widthProperty().bind(
            	    Bindings.min(
            	        this.widthProperty().multiply(slotWidthPercent),
            	        this.heightProperty().multiply(slotHeightPercent)
            	    )
            	);

            // Bind height = width to keep square
            square.heightProperty().bind(square.widthProperty());

            // Add to pane
           	this.getChildren().add(square);
           	colourNodes.add(square);
        }
    }
}
