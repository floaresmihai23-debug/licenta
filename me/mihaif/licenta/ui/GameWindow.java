package me.mihaif.licenta.ui;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameWindow {
	private int width, height;
	private String title;
	private Stage stage;
	
	
	private Scene titleScene;
	private Scene lobbyScene;
    private Scene gameScene;
    private Scene waitingScene;
    
    
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		if(stage!=null) {stage.setWidth(width); stage.setHeight(height);}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title == null) return;
		this.title = title;
		if(stage!=null) stage.setTitle(title);
	}
    public GameWindow(Stage stage, int width, int height, String title) {
		this.stage=stage;
		this.width = width;
		this.height = height;
		this.title = title;
		
		titleScene = new TitleScene(width, height);
		//lobbyScene = new LobbyScene(width, height);
		//gameScene = new GameScene(width, height);
		//waitingScene = new WaitingScene(width, height);
    } 
    public void show() {
        stage.setTitle(title);
        stage.setScene(titleScene);
        stage.show();
    }    
    public void loadTitle() {
    	stage.setScene(titleScene);
    }
    public void loadLobby() {
    	lobbyScene = new LobbyScene(width, height);
    	stage.setScene(lobbyScene);
    }
    public void loadGame() {
    	gameScene = new GameScene(width, height);
    	stage.setScene(gameScene);
    }
    public void loadWaiting() {
    	waitingScene = new WaitingScene(width, height);
    	stage.setScene(waitingScene);
    }
}

