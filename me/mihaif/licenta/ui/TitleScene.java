package me.mihaif.licenta.ui;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import me.mihaif.licenta.Main;

public class TitleScene extends ManagedScene{

	public TitleScene(double width, double height) {
		super(width, height);
	}

	@Override
	protected void initLayout() {
		LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1,  // startX, startY, endX, endY (0-1 relative)
                true,         // proportional
                CycleMethod.NO_CYCLE, 
                new Stop(0, Color.web("#00FF62")),
                new Stop(1, Color.web("#00D4FF"))
        );
		
		this.getRootPane().setBackground(new Background(new BackgroundFill(
                gradient,         // background color
                CornerRadii.EMPTY, // rounded corners
                null                     // insets
        )));
		
		Label title = new Label("sketch.me");
		title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 30));
		title.setTextFill(Color.web("#FFFFFF"));
		title.setAlignment(Pos.CENTER);
		this.addNode(title, 0.25, 0.1, 0.5, 0.05, this.getRootPane());
		
		
		Button singleplayer = new Button("SINGLEPLAYER");
		singleplayer.setBackground(new Background(new BackgroundFill(
				Color.TRANSPARENT,
				new CornerRadii(2),
				null
		)));
		singleplayer.setOnMouseEntered(_ -> {
			this.setCursor(Cursor.HAND);
			singleplayer.setBackground(new Background(new BackgroundFill(
					Color.web("#CC7BEA"),
					new CornerRadii(2),
					null
			)));
			singleplayer.setTextFill(Color.WHITE);
        });
		singleplayer.setOnMouseExited(_ -> {
			this.setCursor(Cursor.DEFAULT);
			singleplayer.setBackground(new Background(new BackgroundFill(
					Color.TRANSPARENT,
					CornerRadii.EMPTY,
					null
			)));
			singleplayer.setTextFill(Color.BLACK);
        });
		singleplayer.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		this.addNode(singleplayer, 0.25, 0.4, 0.5, 0.05, this.getRootPane());
		
		Button multiplayer = new Button("MULTIPLAYER");
		multiplayer.setBackground(new Background(new BackgroundFill(
				Color.TRANSPARENT,
				new CornerRadii(2),
				null
		)));
		multiplayer.setOnMouseEntered(_ -> {
			this.setCursor(Cursor.HAND);
			multiplayer.setBackground(new Background(new BackgroundFill(
					Color.web("#CC7BEA"),
					new CornerRadii(2),
					null
			)));
			multiplayer.setTextFill(Color.WHITE);
        });
		multiplayer.setOnMouseExited(_ -> {
			this.setCursor(Cursor.DEFAULT);
			multiplayer.setBackground(new Background(new BackgroundFill(
					Color.TRANSPARENT,
					CornerRadii.EMPTY,
					null
			)));
			multiplayer.setTextFill(Color.BLACK);
			
        });
		multiplayer.setOnAction(_ ->{
			Main.gameWindow.loadLobby();
		});
		multiplayer.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		this.addNode(multiplayer, 0.25, 0.6, 0.5, 0.05, this.getRootPane());
	}
	



}
