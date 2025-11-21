package me.mihaif.licenta.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import me.mihaif.licenta.ui.utilities.SceneryUtils;

public abstract class ManagedScene extends Scene{

	protected List<Node> nodes;
	protected Pane getRootPane() {
		return (Pane) getRoot();
	}
	public ManagedScene(double width, double height) {
		super(new Pane(), width, height);
		nodes = new ArrayList<Node>();
		initLayout();
	}
	
	protected abstract void initLayout();
	
	protected void addNode(Node node, double layoutXPercent, double layoutYPercent, double widthPercent, double heightPercent, Pane parent) {
	    nodes.add(node);
	    SceneryUtils.linkNodeRelativePositionToParent(node, parent, layoutXPercent, layoutYPercent);
	    SceneryUtils.linkNodeRelativeSizeToParent(node, parent, widthPercent, heightPercent);
	    
	    parent.getChildren().add(node);
	}

}
