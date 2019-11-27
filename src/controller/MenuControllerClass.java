package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import application.Main;
import exceptions.CharacterNull;
import exceptions.NickNameIsNotValid;
import exceptions.UserIsAlreadyTaken;
import exceptions.UserNull;

public class MenuControllerClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void play(ActionEvent event) {
    	
    	TextInputDialog d = new TextInputDialog();
    	d.setTitle("WELCOME!!!!");
    	d.setContentText("please eneter your nickname: ");
    	Optional<String> r = d.showAndWait();
    	if(r.isPresent()) {
    		try {
    			String name = r.get();
    			Main.getIndexModel().registerUsers(name);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Index.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setTitle("INDEX GAME");
				stage.setScene(scene);
				stage.show();
			} catch (NickNameIsNotValid e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.show();
			} catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(UserIsAlreadyTaken e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.show();
			}
    		
    	}
    	
    }

    @FXML
    void points(ActionEvent event) {
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Points.fxml"));
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
    void resumeGame(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Resume.fxml"));
			Parent root = loader.load();
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
    void searchByName(ActionEvent event) {
    	TextInputDialog d = new TextInputDialog();
    	d.setContentText("Enter the user's name you are looking for: ");
    	Optional<String> r = d.showAndWait();
    	if(r.isPresent()) {
    		String c = r.get();
    		try {
    			Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText(Main.getIndexModel().searchUsersByName(c).toString());
				a.show();
			} catch (UserNull e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText(e.getMessage());
				a.show();
			}
    	}
    }

    
   

    @FXML
    void searchCharacter(ActionEvent event) {
    	TextInputDialog d = new TextInputDialog();
    	d.setContentText("Enter the user's name you are looking for: ");
    	Optional<String> r = d.showAndWait();
    	if(r.isPresent()) {
    		String c = r.get();
    		try {
    			Alert a = new Alert(AlertType.INFORMATION);
    			a.setContentText(Main.getIndexModel().searchCharacterByName(c).toString());
    			a.show();
    		}catch(CharacterNull e) {
    			Alert a = new Alert(AlertType.INFORMATION);
    			a.setContentText(e.getMessage());
    			a.show();
    		}
    	}
    }

    @FXML
    void initialize() {

    }
}
