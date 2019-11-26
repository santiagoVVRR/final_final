package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class PointsControllerClass {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

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
    void byBestGame(ActionEvent event) {
    	listView.getItems().clear();
    	if(Main.getIndexModel().getUsers().size() != 0) {
    		String[] u = Main.getIndexModel().writeUsersBestGame().split(",");
    		for(int i = 0; i < u.length; i++) {
    			listView.getItems().add(u[i]);
    		}
    	}
    }

    @FXML
    void byFirstGame(ActionEvent event) {
    	listView.getItems().clear();
    	if(Main.getIndexModel().getUsers().size() != 0) {
    		String[] u = Main.getIndexModel().writeUsersFirstGame().split(",");
    		for(int i = 0; i < u.length; i++) {
    			listView.getItems().add(u[i]);
    		}
    	}
    }

    @FXML
    void byLastGame(ActionEvent event) {
    	listView.getItems().clear();
    	if(Main.getIndexModel().getUsers().size() != 0) {
    		String[] u = Main.getIndexModel().writeUserLastGame().split(",");
    		for(int i = 0; i < u.length; i++) {
    			listView.getItems().add(u[i]);
    		}
    	}
    }

    @FXML
    void byName(ActionEvent event) {
    	listView.getItems().clear();
    	if(Main.getIndexModel().getUsers().size() != 0) {
    		String[] u = Main.getIndexModel().writeUsersNames().split(",");
    		for(int i = 0; i < u.length; i++) {
    			listView.getItems().add(u[i]);
    		}
    	}
    }

    @FXML
    void byPoints(ActionEvent event) {
    	listView.getItems().clear();
    	if(Main.getIndexModel().getUsers().size() != 0) {
    		String[] u = Main.getIndexModel().writeUsersNScores().split(",");
    		for(int i = 0; i < u.length; i++) {
    			listView.getItems().add(u[i]);
    		}
    	}
    }

    @FXML
    void initialize() {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Points.fxml'.";

    }
}
