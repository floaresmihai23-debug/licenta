package me.mihaif.licenta.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import me.mihaif.licenta.Main;
import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.PlayerJoinEvent;
import me.mihaif.licenta.common.events.network.PlayerListReceiveEvent;
import me.mihaif.licenta.common.events.network.StartEvent;
import me.mihaif.licenta.common.events.ui.UIEventBus;
import me.mihaif.licenta.common.events.ui.WaitingSceneLoadedEvent;
import me.mihaif.licenta.network.Client;
import me.mihaif.licenta.network.utilities.PlayerData;

public class WaitingScene extends ManagedScene{

	public WaitingScene(double width, double height) {
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
        userArea.setBackground(new Background(new BackgroundFill(new Color(0.501, 1.00, 0.120, 1.00), new CornerRadii(10), null)));
        userArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        this.addNode(userArea, 0.1, 0, 0.8, 0.9, this.getRootPane());
        
        Pane joinedPlayersListPane = new Pane();
        this.addNode(joinedPlayersListPane, 0.25, 0, 0.5, 1, userArea);
        
        updatePlayerListUI(joinedPlayersListPane);
        NetworkEventBus.getInstance().addListener(StartEvent.class, _ -> {
        	System.out.println("[INFO][GUI] Received Network Notification: Start Game -> Updating UI -> Switching Scenes [WaitingScene => GameScene]");
        	Platform.runLater(() -> {
        		Main.gameWindow.loadGame();
        	});
        });
        NetworkEventBus.getInstance().addListener(PlayerListReceiveEvent.class, _ -> {
        	System.out.println("[INFO][GUI] Received Network Notification: Player List Received -> Synchronizing Player List");
        	Platform.runLater(() -> {
            	updatePlayerListUI(joinedPlayersListPane);
        	});
        });
        NetworkEventBus.getInstance().addListener(PlayerJoinEvent.class, _ -> {
        	System.out.println("[INFO][GUI] Received Network Notification: New Player Joined -> Synchronizing Player List");	
        	Platform.runLater(() -> {
            	updatePlayerListUI(joinedPlayersListPane);
        	});
        });
        
       	UIEventBus.getInstance().fire(new WaitingSceneLoadedEvent());
	}
	private void updatePlayerListUI(Pane pane) {
        pane.getChildren().clear();
        double offsetY = 0.05; // start padding
        List<PlayerData> allPlayers = new ArrayList<PlayerData>();
        allPlayers.add(Client.getInstance().getLocalPlayer());
        allPlayers.addAll(Client.getInstance().getOtherPlayers());
        for (PlayerData player : allPlayers) {
        	Image avatar = new Image(getClass().getResourceAsStream("/assets/avatars/avatar" + player.avatarID + ".png"));
        	
            Pane playerPane = new Pane();
            playerPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
            playerPane.setBackground(new Background(new BackgroundFill(Color.color(1,1,1,0.3), new CornerRadii(3), null)));
            // Proportional sizing
            this.addNode(playerPane, 0, offsetY, 1, 0.15, pane);

            // Label
            Label usernameLabel = new Label(player.username);
            usernameLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
            usernameLabel.setTextFill(Color.BLACK);
            this.addNode(usernameLabel, 0.05, 0.2, 0.9, 0.6, playerPane);

            ImageView avatarView = new ImageView(avatar);
        	avatarView.setPreserveRatio(true);   // keeps aspect ratio
        	avatarView.fitWidthProperty().bind(playerPane.heightProperty().multiply(0.8));
        	avatarView.fitHeightProperty().bind(playerPane.heightProperty().multiply(0.8));
        	avatarView.setPreserveRatio(true);

        	// Bind position relative to parent
        	avatarView.layoutXProperty().bind(
        		playerPane.widthProperty().multiply(0.8)
        	    .subtract(avatarView.fitWidthProperty().divide(2)) // center around point
        	);
        	avatarView.layoutYProperty().bind(
            		playerPane.heightProperty().multiply(0.1)
        			);
        	playerPane.getChildren().add(avatarView);
        	// Optionally, you could add an ImageView for avatar:
            // ImageView avatarView = new ImageView(getAvatarImage(player.avatarID));
            // this.addNode(avatarView, 0.8, 0.1, 0.15, 0.8, playerPane);

            offsetY += 0.18; // spacing between player panes
        }
    

	}

}
