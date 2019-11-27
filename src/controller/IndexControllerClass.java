package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import exceptions.NotChoosenCharacter;
import exceptions.NotChoosenField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IndexControllerClass implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView mapPicture;

    @FXML
    private ImageView characterPicture;

    @FXML
    private Button leftMap;

    @FXML
    private Button rightMap;

    @FXML
    private Button leftCharacter;

    @FXML
    private Button rightCharacter;

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
    void play(ActionEvent event) {
    	try {
    		if(mapPicture.getOpacity() != 0.40) {
    			Main.getIndexModel().selectedField(false);
    		}if(characterPicture.getOpacity() != 0.40) {
    			Main.getIndexModel().selectedCharacter(false);
    		}
    		Main.getIndexModel().selectedField(true);
    		Main.getIndexModel().selectedCharacter(true);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Game.fxml"));
    		Parent root = loader.load();
    		Scene scene = new Scene(root);
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		//GameControllerClass game = (GameControllerClass) loader.getController();
    		//game;
    		stage.setScene(scene);
    		stage.show();
    	} catch (NotChoosenField e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		} catch (NotChoosenCharacter e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		}catch(IOException e) {
			e.printStackTrace();
		}  
    }

    @FXML
    void showLeftCharacter(ActionEvent event) {
    	Main.getIndexModel().visualizePrevCharacter();
    	characterPicture.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
    }

    @FXML
    void showLeftMap(ActionEvent event) {
    	Main.getIndexModel().visualizePrevField();
    	mapPicture.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
    }

    @FXML
    void showRightCharacter(ActionEvent event) {
    	Main.getIndexModel().visualizeNextCharacter();
    	characterPicture.setImage(new Image(Main.getIndexModel().getCharacterChoose().getImage()));
    }

    @FXML
    void showRightMap(ActionEvent event) {
    	Main.getIndexModel().visualizeNextField();
    	mapPicture.setImage(new Image(Main.getIndexModel().getFieldChoose().getImage()));
    }
    
    @FXML
    void characterChoice(MouseEvent event) {
    	characterPicture.setOpacity(0.40);
    	leftCharacter.setDisable(true);
    	rightCharacter.setDisable(true);
    }

    @FXML
    void mapChoice(MouseEvent event) {
    	mapPicture.setOpacity(0.40);
    	leftMap.setDisable(true);
    	rightMap.setDisable(true);
    }

   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//System.out.println(Main.getIndexModel().getUpField().getImage());
		characterPicture.setImage(new Image((String)Main.getIndexModel().getUpCharacter().getImage()));
		mapPicture.setImage(new Image(Main.getIndexModel().getUpField().getImage()));
		
	}
}
