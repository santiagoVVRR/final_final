package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IndexControllerClass {

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

    }

    @FXML
    void showLeftCharacter(ActionEvent event) {

    }

    @FXML
    void showLeftMap(ActionEvent event) {

    }

    @FXML
    void showRightCharacter(ActionEvent event) {

    }

    @FXML
    void showRightMap(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert mapPicture != null : "fx:id=\"mapPicture\" was not injected: check your FXML file 'Index.fxml'.";
        assert characterPicture != null : "fx:id=\"characterPicture\" was not injected: check your FXML file 'Index.fxml'.";
        assert leftMap != null : "fx:id=\"leftMap\" was not injected: check your FXML file 'Index.fxml'.";
        assert rightMap != null : "fx:id=\"rightMap\" was not injected: check your FXML file 'Index.fxml'.";
        assert leftCharacter != null : "fx:id=\"leftCharacter\" was not injected: check your FXML file 'Index.fxml'.";
        assert rightCharacter != null : "fx:id=\"rightCharacter\" was not injected: check your FXML file 'Index.fxml'.";

    }
}
