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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
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
				
			}if(win) {
				win();
			}
		}),new KeyFrame(Duration.millis(20)));
		
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
		}),new KeyFrame(Duration.millis(20)));
		
		trapThread.setCycleCount(Animation.INDEFINITE);
		trapThread.play();
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
		a.setContentText("SCORE. "+(100-c));
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
	
	public void SceneInitializer() {
		lifesImg = new ArrayList<>();
		lifesImg.add(heart1);
		lifesImg.add(heart2);
		lifesImg.add(heart3);
		lifesImg.add(heart4);
		lifesImg.add(heart5);
		lifesImg.add(heart6);
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
		goldImg.setOpacity(0-30);
		emreldImg.setOpacity(0.30);
		diamondImg.setOpacity(0.30);
		player.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
		
	}
}
