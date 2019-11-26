package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import exceptions.UserNull;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ResumeGameControllerClass implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button btnSlect;

    @FXML
    private Button btnHome;

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
    void select(ActionEvent event) {
    	try {
    		String m = "";
    		ObservableList<String> users;
    		users = listView.getSelectionModel().getSelectedItems();
    		Main.getIndexModel().userChoosenInfo(users.get(0));
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Index.fxml"));
    		Parent root = loader.load();
    		Scene scene = new Scene(root);
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
    	}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNull e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Resume.fxml'.";
        assert btnSlect != null : "fx:id=\"btnSlect\" was not injected: check your FXML file 'Resume.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Resume.fxml'.";

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(Main.getIndexModel().getUsers().size() != 0) {
			String[] u = Main.getIndexModel().writeUsersNames().split(",");
			for(int i = 0; i < u.length; i++) {
				listView.getItems().add(u[i]);
			}
		}
	}
}
