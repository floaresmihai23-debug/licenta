package me.mihaif.licenta.ui;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import me.mihaif.licenta.common.events.network.DrawEvent;
import me.mihaif.licenta.common.events.network.NetworkEventBus;

public class GameScene extends ManagedScene{

	public GameScene(double width, double height) {
		super(width, height);
	}
	
	@Override
	protected void initLayout() {
		this.getRootPane().setBackground(new Background(new BackgroundFill(
                new Color(0.860, 0.859, 0.843, 1.0),         // background color
                CornerRadii.EMPTY, // rounded corners
                null                     // insets
        )));
		
		Pane userArea = new Pane();
        userArea.setBackground(new Background(new BackgroundFill(new Color(1.00, 0.929, 0.150, 1.00), new CornerRadii(10), null)));
        userArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        this.addNode(userArea, 0.1, 0, 0.8, 0.9, this.getRootPane());

        DrawingCanvas drawingCanvas = new DrawingCanvas();
        drawingCanvas.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
        drawingCanvas.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
	    NetworkEventBus.getInstance().addListener(DrawEvent.class, (event) -> {
	    	Platform.runLater(() -> { //sync the Network thread with UI thread
	    		var gc = drawingCanvas.getCanvas().getGraphicsContext2D();
	            gc.setLineCap(StrokeLineCap.ROUND);
	            gc.setLineJoin(StrokeLineJoin.ROUND);
	            gc.setStroke(event.colour);
	            gc.setLineWidth(event.thickness);
	            gc.strokeLine(event.x1, event.y1, event.x2, event.y2);
	            
	    	});
	    });
	    
       
        this.addNode(drawingCanvas, 0, 0.15, 1.0, 0.7, userArea);

        ColourPallete colourPallete = new ColourPallete(drawingCanvas);
        this.addNode(colourPallete, 0.3, 0.85, 0.4, 0.15, userArea);
        
        ToolBar toolBar = new ToolBar(drawingCanvas);
        this.addNode(toolBar, 0, 0.85, 0.3, 0.15, userArea);
	}

}
