package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Poison;
import model.Stone;
import model.Trap;
import thread.ThreadTimer;

public class GameControllerClass implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView map;
    
    @FXML
    private ImageView player;

    @FXML
    private ImageView heart1;

    @FXML
    private ImageView heart2;

    @FXML
    private ImageView heart3;

    @FXML
    private ImageView heart4;

    @FXML
    private ImageView heart5;

    @FXML
    private ImageView heart6;

    @FXML
    private ImageView diamondImg;

    @FXML
    private ImageView emreldImg;

    @FXML
    private ImageView goldImg;

    @FXML
    private ImageView ironImg;

    @FXML
    private Button btnHome;
    
    @FXML
    private Text time;
    
    //Class Attributes
    
    private ArrayList<ImageView> lifesImg;
    
    private ArrayList<ImageView> trapsImg;
    
    private ArrayList<ImageView> stonesImg;
    
    private ArrayList<Stone> stone;
    
    private ArrayList<Trap> traps;
    
    private Timeline regularThread;
    
    private Timeline trapThread;
    
    private boolean alive;
    
    private boolean win;
    
    private ThreadTimer tt;
    
    private AnimationTimer at;
    

    @FXML
    void backHome(ActionEvent event) {

    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Menu.fxml"));
	    	Parent root;
			root = loader.load();
			Scene scene = new Scene(root);
	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
       

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		alive = true;
		win = false;
		SceneInitializer();
		
		regularThread = new Timeline(new KeyFrame(Duration.ZERO, e->{
			if(alive) {
				takeStone();
			}else {
				//lose();
			}if(win) {
				win();
			}
		}),new KeyFrame(Duration.millis(30)));
		
		regularThread.setCycleCount(Animation.INDEFINITE);
		regularThread.play();
		
		trapThread = new Timeline(new KeyFrame(Duration.ZERO, e->{
			if(alive) {
				takeTrap();
			}else {
				lose();
			}if(win){
				trapThread.stop();
			}
		}),new KeyFrame(Duration.millis(30)));
		
		trapThread.setCycleCount(Animation.INDEFINITE);
		trapThread.play();
	}
	
	public void takeTrap() {
		for(int i = 0; i < traps.size(); i++) {
			traps.get(i).move();
			trapsImg.get(i).setLayoutX(traps.get(i).getX());
			trapsImg.get(i).setLayoutY(traps.get(i).getY());
			if(Main.getIndexModel().getCharacterChoose().take((int)trapsImg.get(i).getLayoutX(), (int)trapsImg.get(i).getLayoutY())) {
				if(trapsImg.get(i).isVisible()) {
					if(traps.get(i) instanceof Poison) {
						int cont = 0;
						for(int j = 0; j < lifesImg.size() && cont < 2; j++) {
							if(lifesImg.get(j).isVisible()) {
								lifesImg.get(j).setVisible(false);
								cont++;
							}
						}
					}else {
						int cont = 0;
						for(int j = 0; j < lifesImg.size() && cont <1; j++) {
							if(lifesImg.get(j).isVisible()) {
								lifesImg.get(j).setVisible(false);
								cont++;
							}
						}
					}
				}
				trapsImg.get(i).setVisible(false);
			}
		}
		if(!lifesImg.get(5).isVisible()) {
			alive = false;
			System.out.println("perdiste por malo");
		}
	}
	
	public void takeStone() {
		for(int i = 0; i < stone.size(); i++) {
			stone.get(i).move();
			stonesImg.get(i).setLayoutX(stone.get(i).getPosx());
			stonesImg.get(i).setLayoutY(stone.get(i).getPosy());
			if(Main.getIndexModel().getCharacterChoose().take((int)stonesImg.get(i).getLayoutX(), (int)stonesImg.get(i).getLayoutY())) {
				stonesImg.get(i).setVisible(false);
				Main.getIndexModel().getCharacterChoose().grabbStone(stone.get(i));
				if(stone.get(i).getPower() == 1) {
					ironImg.setOpacity(1);
				}else if(stone.get(i).getPower() == 2) {
					goldImg.setOpacity(1);
				}else if(stone.get(i).getPower() == 3) {
					emreldImg.setOpacity(1);
				}else if(stone.get(i).getPower() == 4) {
					diamondImg.setOpacity(1);
				}
			}
		}
		if(ironImg.getOpacity() == 1.0 && goldImg.getOpacity() == 1.0 && emreldImg.getOpacity() == 1.0 && diamondImg.getOpacity() == 1.0) {
			win = true;
		}
	}
	
	private void win() {
		regularThread.stop();
		tt.stop();
		at.stop();
		Main.getIndexModel().getUserChoose().bestGame(time.getText());
		Main.getIndexModel().getUserChoose().setLastGame(time.getText());
		Main.getIndexModel().getUserChoose().setFirstGame(time.getText());
		setPoints();
		int c = 0;
		String[] aux = time.getText().split(":");
		int sec = Integer.parseInt(aux[1]);
		int min = Integer.parseInt(aux[0]);
		c = (min*60)+sec;
		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("SCORE: "+(100-c));
		a.show();
		btnHome.setVisible(true);
	}
	
	public void lose() {
		trapThread.stop();
		regularThread.stop();
		at.stop();
		tt.stop();
		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("LOOSER!!!! YOUR SCORE IS 0");
		a.show();
		btnHome.setVisible(true);
	}
	
	public void setPoints() {
		int score = 0;
		int c = 0;
		String[] aux = time.getText().split(":");
		int sec = Integer.parseInt(aux[1]);
		int min = Integer.parseInt(aux[0]);
		c = (min*60)+sec;
		score = (int)(100-c*0.5);
		Main.getIndexModel().getUserChoose().setScore(Main.getIndexModel().getUserChoose().getScore()+score);
	}
	
	public void timer() {
		tt = new ThreadTimer(Main.getIndexModel().getFieldChoose(),time);
		tt.start();
	}
	
	private void trapsGenerator() {
		//Poison
		URL poison = getClass().getResource("/images/poison.png");
		Image veneno = new Image(poison.toString(),50,50,false,true);
		
		//Enemys
		//URL pompom = getClass().getResource("/images/creeper.png");
		//Image creeper = new Image(pompom.toString(),50,50,false,true);
		
		URL araña = getClass().getResource("/images/spider.png");
		Image spider = new Image(araña.toString(),50,50,false,true);
		/*
		URL dead = getClass().getResource("/images/zombie.png");
		Image zombie = new Image(dead.toString(),50,50,false,true);
		
		URL huesos = getClass().getResource("/images/esqueleto.png");
		Image archer = new Image(huesos.toString(),50,50,false,true);
		*/
		traps = Main.getIndexModel().getFieldChoose().getTraps();
		for(int i = 0; i < traps.size(); i++) {
			trapsImg.add(new ImageView());
			System.out.println("in");
			if(traps.get(i) instanceof Poison) {
				trapsImg.get(i).setImage(veneno);
			}else {
			//	trapsImg.get(i).setImage(creeper);
				trapsImg.get(i).setImage(spider);
			//	trapsImg.get(2).setImage(zombie);
			//	trapsImg.get(4).setImage(archer);
			}
		}
		pane.getChildren().addAll(trapsImg);
	}
	
	private void stonesGenerator() {
		URL ss1 = getClass().getResource("/images/iron.png");
		Image s1 = new Image(ss1.toString(),50,50,false,true);
		
		URL ss2 = getClass().getResource("/images/gold.png");
		Image s2 = new Image(ss2.toString(),50,50,false,true);
		
		URL ss3 = getClass().getResource("/images/Emerald.png");
		Image s3 = new Image(ss3.toString(),50,50,false,true);
		
		URL ss4 = getClass().getResource("/images/diamond.png");
		Image s4 = new Image(ss4.toString(),50,50,false,true);
		
		Main.getIndexModel().getFieldChoose().addStone(1);
		Main.getIndexModel().getFieldChoose().addStone(2);
		Main.getIndexModel().getFieldChoose().addStone(3);
		Main.getIndexModel().getFieldChoose().addStone(4);
		
		stone = Main.getIndexModel().getFieldChoose().showListOfStones();
		for(int  i = 0; i < stone.size(); i++) {
			stonesImg.add(new ImageView());
			stonesImg.get(0).setImage(s1);
			stonesImg.get(1).setImage(s2);
			stonesImg.get(2).setImage(s3);
			stonesImg.get(3).setImage(s4);
		}
		
		
		pane.getChildren().addAll(stonesImg);
	}
	
	public void SceneInitializer() {
		lifesImg = new ArrayList<>();
		lifesImg.add(0,heart1);
		lifesImg.add(1,heart2);
		lifesImg.add(2,heart3);
		lifesImg.add(3,heart4);
		lifesImg.add(4,heart5);
		lifesImg.add(5,heart6);
		timer();
		trapsImg = new ArrayList<>();
		traps = new ArrayList<>();
		stonesImg = new ArrayList<>();
		stone = new ArrayList<>();
		stonesGenerator();
		trapsGenerator();
		Main.getIndexModel().getFieldChoose().loadStones();
		map.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
		ironImg.setOpacity(0.30);
		goldImg.setOpacity(0.30);
		emreldImg.setOpacity(0.30);
		diamondImg.setOpacity(0.30);
		player.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
		
	}
	
	private void save() {
		try {
			Main.getIndexModel().UsersSerialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void moving() {
		at = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				Main.getIndexModel().getCharacterChoose().move();
				int x = Main.getIndexModel().getCharacterChoose().getPosx();
				int y = Main.getIndexModel().getCharacterChoose().getPosy();
				player.relocate(x, y);
			}
		};
		at.start();
	}
	
	public void sceneRecived(Scene s) {
		onKeyPressed(s);
		onKeyReleased(s);
		moving();
	}
	
	
	public void onKeyPressed(Scene s) {
		s.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case LEFT:
					Main.getIndexModel().getCharacterChoose().setLeft(true);
					break;
				case RIGHT:
					Main.getIndexModel().getCharacterChoose().setRight(true);
					break;
				}
				
			}
			
		});
	}
	
	private void onKeyReleased(Scene s) {
		s.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case LEFT:
					Main.getIndexModel().getCharacterChoose().setLeft(false);
					break;
				case RIGHT:
					Main.getIndexModel().getCharacterChoose().setRight(false);
					break;
				}
				
			}
			
		});
	}
	
	
}
