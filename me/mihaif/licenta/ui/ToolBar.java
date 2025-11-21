package me.mihaif.licenta.ui;

import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class ToolBar extends Pane{
	private Slider thicknessController;
	private DrawingCanvas canvas;
	public DrawingCanvas getLinkedCanvas() {
		return canvas;
	}
	public ToolBar(DrawingCanvas canvas) {
		this.canvas = canvas;
		addTools();
	}
	private void addTools() {
		//this.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(10), null)));
		thicknessController = new Slider();

		
		thicknessController.layoutXProperty().bind(this.widthProperty().subtract(thicknessController.widthProperty()).divide(2));
		thicknessController.prefWidthProperty().bind(this.widthProperty().multiply(0.9));
		thicknessController.prefHeightProperty().bind(this.heightProperty().multiply(0.3));
        
		thicknessController.valueProperty().addListener((_, _, newVal) -> {
		    System.out.println("Slider moved: " + newVal.doubleValue());
		    canvas.setCurrentThickness(newVal.doubleValue());
		});
		thicknessController.setMin(1);
		thicknessController.setMax(30);
		thicknessController.setValue(5);
		
		this.getChildren().add(thicknessController);
		
	}
}
