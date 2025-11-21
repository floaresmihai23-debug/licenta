package me.mihaif.licenta.ui;


import java.util.Random;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import me.mihaif.licenta.Main;
import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.WelcomeEvent;
import me.mihaif.licenta.network.Client;
public class LobbyScene extends ManagedScene{

	Canvas canvas = new Canvas();
	private static final double CELL_SIZE = 15;
	public LobbyScene(double width, double height) {
		super(width, height);
	}

	@Override
	protected void initLayout() {
		this.getRootPane().setBackground(new Background(new BackgroundFill(
                new Color(0.860, 0.859, 0.843, 1.0),         // background color
                CornerRadii.EMPTY, // rounded corners
                null                     // insets
        )));
		Canvas canvas = new Canvas();
		canvas.widthProperty().bind(this.getRootPane().widthProperty());
        canvas.heightProperty().bind(this.getRootPane().heightProperty());

        this.getRootPane().getChildren().add(canvas);

        
        canvas.widthProperty().addListener((_, _, _) -> drawGrid(canvas));
        canvas.heightProperty().addListener((_, _, _) -> drawGrid(canvas));

        drawGrid(canvas);
        Random random = new Random();
        String[] imagePaths = {
                "assets/misc/tree.png",
                "assets/misc/sunflower.png",
                "assets/misc/mermaid.png",
                "assets/misc/cloud.png",
                "assets/misc/bubbles.png",
                "assets/misc/unicorn.png",
        };
                

        
            Image image = new Image(imagePaths[0]);
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setRotate(random.nextDouble() * 360);
            this.addNode(imageView, 0.1, 0.05, 0.3, 0.3, this.getRootPane());
            
            Image image1 = new Image(imagePaths[1]);
            ImageView imageView1 = new ImageView(image1);
            imageView1.setPreserveRatio(true);
            imageView1.setRotate(random.nextDouble() * 360);
            this.addNode(imageView1, 0.05, 0.35, 0.25, 0.25, this.getRootPane());
            
            Image image2 = new Image(imagePaths[2]);
            ImageView imageView2 = new ImageView(image2);
            imageView2.setPreserveRatio(true);
            imageView2.setRotate(random.nextDouble() * 360);
            this.addNode(imageView2, 0.1, 0.65, 0.35, 0.35, this.getRootPane());
            
            Image image3 = new Image(imagePaths[3]);
            ImageView imageView3 = new ImageView(image3);
            imageView3.setPreserveRatio(true);
            imageView3.setRotate(random.nextDouble() * 360);
            this.addNode(imageView3, 0.7, 0.05, 0.25, 0.25, this.getRootPane());
            
            
            Image image4 = new Image(imagePaths[4]);
            ImageView imageView4 = new ImageView(image4);
            imageView4.setPreserveRatio(true);
            imageView4.setRotate(random.nextDouble() * 360);
            this.addNode(imageView4, 0.8, 0.35, 0.25, 0.25, this.getRootPane());
            
            Image image5 = new Image(imagePaths[5]);
            ImageView imageView5 = new ImageView(image5);
            imageView5.setPreserveRatio(true);
            imageView5.setRotate(random.nextDouble() * 360);
            this.addNode(imageView5, 0.7, 0.65, 0.30, 0.30, this.getRootPane());
        
        TextField usernameField = new TextField("Mihai");
        usernameField.setPromptText("Username");
        usernameField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        usernameField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
        
        this.addNode(usernameField, 0.5, 0.3, 0.5, 0.05, this.getRootPane());
        usernameField.translateXProperty().bind(usernameField.widthProperty().divide(-2));
        
        
        TextField ipField = new TextField("localhost");
        ipField.setPromptText("IP");
        ipField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        ipField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
        
        this.addNode(ipField, 0.5, 0.4, 0.5, 0.05, this.getRootPane());
        ipField.translateXProperty().bind(ipField.widthProperty().divide(-2));
        
        TextField portField = new TextField("5000");
        portField.setPromptText("Port");
        portField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        portField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
        
        this.addNode(portField, 0.5, 0.5, 0.5, 0.05, this.getRootPane());
        portField.translateXProperty().bind(portField.widthProperty().divide(-2));
        
        Image connectButtonImage = new Image(getClass().getResourceAsStream("/assets/misc/connect.png"));
        Image connectButtonImageOnMouseOver = new Image(getClass().getResourceAsStream("/assets/misc/connect_onmouseover.png"));
        
        Label errorMessage = new Label("");
        this.addNode(errorMessage, 0.5, 0.8, 1, 0.05, this.getRootPane());
        errorMessage.translateXProperty().bind(errorMessage.widthProperty().divide(-2));
        errorMessage.setAlignment(Pos.CENTER);
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        
        Pane connectButtonPane = new Pane();
        ImageView connectButton = new ImageView(connectButtonImage);
        connectButton.fitWidthProperty().bind(connectButtonPane.widthProperty());
        connectButton.fitHeightProperty().bind(connectButtonPane.heightProperty());

        connectButton.layoutXProperty().bind(connectButtonPane.widthProperty().subtract(connectButton.fitWidthProperty()).divide(2));
        connectButton.layoutYProperty().bind(connectButtonPane.heightProperty().subtract(connectButton.fitHeightProperty()).divide(2));
    	connectButtonPane.getChildren().add(connectButton);
        connectButtonPane.setOnMouseEntered(_ -> {
        	this.setCursor(Cursor.HAND);
    		connectButton.setImage(connectButtonImageOnMouseOver);
    	});
    	connectButtonPane.setOnMouseExited(_ -> {
    		this.setCursor(Cursor.DEFAULT);
    		connectButton.setImage(connectButtonImage);
    	});
    	connectButton.setOnMouseClicked(_ -> {
            if(usernameField.getText().equals("") || ipField.getText().equals("") || portField.getText().equals("")) {
            	errorMessage.setText("Please specify username, IP address and port in order to connect!");
            }
            Client client = Client.getInstance();
            client.setHost(ipField.getText());
            client.setPort(Integer.parseInt(portField.getText()));
            client.setUsername(usernameField.getText());

            client.startAsync();
            NetworkEventBus.getInstance().addListener(WelcomeEvent.class, _ -> {
            	Platform.runLater(() -> {
            		System.out.println("[INFO][GUI] Received Network Notification: Welcome -> Switching Scenes [LobbyScene => WaitingScene]");
            		Main.gameWindow.loadWaiting();
            	});
            });
        });
        this.addNode(connectButtonPane, 0.25, 0.7, 0.5, 0.1, this.getRootPane());
    	
        
        
    	
        /*Button connectButton = new Button();
        connectButton.setText("CONNECT");
        connectButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        connectButton.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1), CornerRadii.EMPTY, null)));
        
        this.addNode(connectButton, 0.5, 0.7, 0.5, 0.1, this.getRootPane());
        connectButton.translateXProperty().bind(connectButton.widthProperty().divide(-2));
        
        
        connectButton.setOnAction(e -> {
            if(usernameField.getText().equals("") || ipField.getText().equals("") || portField.getText().equals("")) {
            	errorMessage.setText("Please specify username, IP address and port in order to connect!");
            }
            Client client = Client.getInstance();
            client.setHost(ipField.getText());
            client.setPort(Integer.parseInt(portField.getText()));
            client.setUsername(usernameField.getText());

            client.addErrorListener(msg -> Platform.runLater(() -> errorMessage.setText(msg)));
            client.startAsync();
            Client.getInstance().addConnectionStateListener(event -> Platform.runLater(() -> {
            	Main.gameWindow.loadWaiting();
            }));
        });
        */
	}

    private void drawGrid(Canvas canvas) {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        gc.setStroke(new Color(0.8, 0.8, 0.8, 0.5));
        gc.setLineWidth(1);

        // Draw vertical lines
        for (double x = 0; x < width; x += CELL_SIZE) {
            gc.strokeLine(x, 0, x, height);
        }

        // Draw horizontal lines
        for (double y = 0; y < height; y += CELL_SIZE) {
            gc.strokeLine(0, y, width, y);
        }
    }

}
