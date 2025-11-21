package me.mihaif.licenta.ui.utilities;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SceneryUtils {
	public static void linkNodeRelativePositionToParent(Node node, Pane parent, double layoutXPercent, double layoutYPercent) {
		node.layoutXProperty().bind(parent.widthProperty().multiply(layoutXPercent));
	    node.layoutYProperty().bind(parent.heightProperty().multiply(layoutYPercent));

	}
	public static void linkNodeRelativeSizeToParent(Node node, Pane parent, double widthPercent, double heightPercent) {
		switch (node) {
	    	case Region r -> {
	        	r.prefWidthProperty().bind(parent.widthProperty().multiply(widthPercent));
	        	r.prefHeightProperty().bind(parent.heightProperty().multiply(heightPercent));
	    	}
	    	case Rectangle r -> {
	        	r.widthProperty().bind(parent.widthProperty().multiply(widthPercent));
	        	r.heightProperty().bind(parent.heightProperty().multiply(heightPercent));
	    	}
	    	case Circle c -> {
	        	c.radiusProperty().bind(
	        		parent.widthProperty()
	                  	.multiply(Math.min(widthPercent, heightPercent))
	                  	.divide(2)
	        		);
	    	}
	    	case Ellipse e -> {
	        	e.radiusXProperty().bind(parent.widthProperty().multiply(widthPercent / 2));
	        	e.radiusYProperty().bind(parent.heightProperty().multiply(heightPercent / 2));
	    	}
	    	case ImageView img -> {
	            img.fitWidthProperty().bind(parent.widthProperty().multiply(widthPercent));
	            img.fitHeightProperty().bind(parent.heightProperty().multiply(heightPercent));
	            img.setPreserveRatio(true);
	        }
	    	default -> {
	    		System.err.println("Unhandled node type: " + node.getClass().getSimpleName());
	    	}
		}
	}
}
