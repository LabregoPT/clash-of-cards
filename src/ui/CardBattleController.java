/**
 * Sample Skeleton for 'GUI.fxml' Controller Class
 */

package ui;

import model.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CardBattleController {
	
	private Game g;
	
	private Player p;

    @FXML // fx:id="x1"
    private Font x1; // Value injected by FXMLLoader

    @FXML // fx:id="x2"
    private Color x2; // Value injected by FXMLLoader

    @FXML
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML
    private Label attackLabel; // Value injected by FXMLLoader

    @FXML
    private ImageView player1Pic; // Value injected by FXMLLoader

    @FXML
    private ImageView player2Pic; // Value injected by FXMLLoader

    @FXML
    private Label p1AttackPoints; // Value injected by FXMLLoader

    @FXML
    private Label p1LifePoints; // Value injected by FXMLLoader

    @FXML
    private Label p2AttackPoints; // Value injected by FXMLLoader

    @FXML
    private Label p2LifePoints; // Value injected by FXMLLoader

    @FXML
    private ImageView imageViewCard; 

    @FXML
    private Font x3; 

    @FXML
    private Color x4;

    @FXML
    private Label turnsLeftlabel; 

    @FXML
    void exit(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    void fightButton(ActionEvent event) {

    }

    @FXML
    void loadGameMenu(ActionEvent event) {

    }

    @FXML
    void newGameMenu(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("");
    	dialog.setTitle("Text Input Dialog");
    	dialog.setHeaderText("Look, a Text Input Dialog");
    	dialog.setContentText("Please enter your name:");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		if (result.get().isEmpty()) {
    			newGameMenu(event);
			}else {
				g = new Game();
	    		Player newPlayer = new Player(result.get());
	    		System.out.println(result.get());
			}
    		
//    		
    	}

    	
    	
    }

    @FXML
    void prevCard(ActionEvent event) {

    }

    @FXML
    void saveGameMenu(ActionEvent event){
    	try {
    		g.saveGame();
    	}catch(IOException e) {
    		Alert t = new Alert(AlertType.ERROR);
    		t.setTitle("Error");
    		t.setContentText("Failed to load certain files");
    	}
    }
    @FXML
    void selectCard(ActionEvent event) {
    	if(p!=null) {
    		int i = (int) (Math.random() * 100);
    		i = i%10;
    		p.selectCard(i);
    		System.out.println("Here");
    	}else {
    		Alert t = new Alert(AlertType.ERROR);
    		t.setContentText("There is no player");
    	}
    }

}
