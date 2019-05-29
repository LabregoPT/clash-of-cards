package ui;
import model.*;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class to control each of the different events happening in the GUI.
 * @author Jhon Edward Mora - Andres Orozco Nuñez
 *
 */
public class CardBattleController {
    
	/**
     * This is a relation between the model and the interface
     */
	private Game model;
	/**
	 * This label display the  name of the player's deck card
	 */
	@FXML
    private Label deckCardName;
	/**
	 * This is used to show image of the player's deck card
	 */
    @FXML
    private ImageView deckCardImage;
    /**
	 * This show the first attribute from player's deck card
	 */
    @FXML
    private Label att1Label;
    /**
	 * This show the second attribute from player's deck card
	 */
    @FXML
    private Label att2Label;
    /**
	 * This show the last attribute from player's deck card
	 */
    @FXML
    private Label att3Label;
    /**
	 * This show the enemy card's name
	 */
    @FXML
    private Label enemyCardName;
    /**
	 * This show the enemy's card picture
	 */
    @FXML
    private ImageView deckCardImage2;
    /**
	 * This show the first attribute from the enemy's card
	 */
    @FXML
    private Label enemyAtt1Label;
    /**
	 * This show the second attribute from the enemy's card
	 */
    @FXML
    private Label enemyAtt2Label;
    /**
	 * This show the last attribute from the enemy's card
	 */
    @FXML
    private Label enemyAtt3Label;
    /**
	 * This show the selected card name
	 */
    @FXML
    private Label battleCardName;
    /**
	 * This shows the selected card picture
	 */
    @FXML
    private ImageView battleCardImage;
    /**
	 * Represent the first attribute from the selected card
	 */
    @FXML
    private Label battleAtt1Label;
    /**
	 * Represent the second attribute from the selected card
	 */
    @FXML
    private Label battleAtt2Label;
    /**
	 * Represent the last attribute from the selected card
	 */
    @FXML
    private Label battleAtt3Label;

    /**
	 * Label to represent the attribute that will rule the battle.
	 */
    @FXML
    private Label battleAttributeLabel;
    
    /**
     * DIsplays the Wins / Losses / Draws
     */
    @FXML
    private Label wldLabel;
    
    /**
     * Index of the current card of the player's deck
     */
    private int currentIndex;
    
    /**
     * The current selected card by the player.
     */
    private Card selected;

    /**
     * The card selected by the enemy
     */
    private Card enemy;
    
    /**
     * The attribute that will determine the battle winner
     */
    private String battledAttribute;
    
    /**
     * List of attributes in this game.
     */
    private List<Attribute> attributes;

    /**
     * This method initialize the necesary stuff for the game to work
     */
    @FXML
    void initialize() {
    	model = new Game();
    	wldLabel.setText("Wins/Losses/Draws: " +model.getWins()+"/"+model.getLosses()+"/"+model.getDraws());
    }
    
    /**
     * This method loads the full deck 
     */
    @FXML
    void loadDeck() {
    	try {
    		Stage s = new Stage();
        	FileChooser fc = new FileChooser();
        	fc.setTitle("Select the attributes file");
        	String attPath = fc.showOpenDialog(s).getPath();
        	fc.setTitle("Select the cards file");
        	String cards = fc.showOpenDialog(s).getPath();
        	model.generateCards(attPath, cards);
        	attributes = model.getDeck().getAttributes();
        	setupFight();
        	currentIndex = 0;
    	}catch(IOException e) {
    		Alert err = new Alert(AlertType.ERROR);
    		err.setContentText("There was an error loading the files");
    		err.showAndWait();
    	}
    }
    
    /**
     * This method show the about stuff
     * @param event 
     */
    @FXML
    void aboutPressed(ActionEvent event) {
    	VBox vb = new VBox();
    	Text info = new Text("Clash of cards is a game created by a pair of college students as part of their learning process. Copyright Disclaimer to all the copyrighted material here used, as it is only used for learning purposes.\nFeel free to improve! nwn");
    	info.setWrappingWidth(150);
    	vb.getChildren().add(info);
    	Scene s = new Scene(vb, 178, 150);
    	Stage st = new Stage();
    	st.setScene(s);
    	st.showAndWait();
    }

    /**
     * This method exit the programme
     * @param event
     */
    @FXML
    void exit(ActionEvent event) {
    	System.exit(1);
    }

    /**
	 * This method set the battle it is linked to the battle method in the game    
	 * @param event
	 */
    @FXML
    void fightButtonPressed(ActionEvent event) {
    	if(model.getDeck() == null) {
    		Alert err = new Alert(AlertType.INFORMATION);
    		err.setContentText("You can't battle without decks!");
    		err.showAndWait();
    	}else if(selected == null) {
    		Alert err = new Alert(AlertType.INFORMATION);
    		err.setContentText("Select a card to battle first.");
    		err.showAndWait();
    	}else {
    		model.battle(selected, enemy, battledAttribute, model.getPlayer());
    		wldLabel.setText("Wins/Losses/Draws: " +model.getWins()+"/"+model.getLosses()+"/"+model.getDraws());
    		setupFight();
    	}
    }

    /**
     * This load a previous game
     * @param event
     */
    @FXML
    void loadGameMenu(ActionEvent event) {
    	try {
    		model = model.loadGame();
    	}catch(IOException | ClassNotFoundException e) {
    		Alert err = new Alert(AlertType.ERROR);
    		err.setContentText("There was an error loading the files");
    		err.showAndWait();
    	}
    }
   
    /**
     * This create a new player 
     * @param event
     */
    @FXML
    void newGameMenu(ActionEvent event) {
    	if(model.getDeck() != null) {
    		TextInputDialog dg = new TextInputDialog();
    		dg.setTitle("Input");
    		dg.setHeaderText("Provide a Nickname");
    		dg.setContentText("Please provide an user name:");
    		Optional<String> result = dg.showAndWait();
    		if(result.isPresent()) {
    			model.setPlayer(result.get());
    			displayCurrCard();
    		}
    	}else {
    		Alert al = new Alert(AlertType.INFORMATION);
    		al.setTitle("Error");
    		al.setContentText("You must create a deck first.");
    		al.showAndWait();
    	}
    }

    /**
     * This is used to get to the next card in the data structure
     * @param event
     */
    @FXML
    void nextCard(ActionEvent event) {
    	if(model.getPlayer() != null) {
    		if(currentIndex<model.getPlayer().getDeck().getPartial().size()-1) {
        		currentIndex++;
        	}else {
        		currentIndex = 0;
        	}
        	displayCurrCard();
    	}else {
    		Alert al = new Alert(AlertType.INFORMATION);
    		al.setTitle("Error");
    		al.setContentText("You must add a player first.");
    		al.showAndWait();
    	}
    }

    /**
     * This is used to get the previous card
     * @param event
     */
    @FXML
    void prevCard(ActionEvent event) {
    	if(model.getPlayer() != null) {
    		if(currentIndex>0) {
        		currentIndex--;
        	}else {
        		currentIndex = model.getPlayer().getDeck().getPartial().size()-1;
        	}
        	displayCurrCard(); 	
    	}else {
    		Alert al = new Alert(AlertType.INFORMATION);
    		al.setTitle("Error");
    		al.setContentText("You must add a player first.");
    		al.showAndWait();
    	}
    }
    /**
     * This method displays the currently selectioned card 
     */
    void displayCurrCard() {
    	Card toDisplay = model.getPlayer().getDeck().getPartial().get(currentIndex);
    	deckCardName.setText(toDisplay.getName());
    	Image im = new Image(toDisplay.image);
    	deckCardImage.setImage(im);
    	Attribute a1 = toDisplay.getAttributes()[0];
    	Attribute a2 = toDisplay.getAttributes()[1];
    	Attribute a3 = toDisplay.getAttributes()[2];
    	att1Label.setText(a1.getName() + " : " + a1.getPower());
    	if(a2 != null) {
    		att2Label.setVisible(true);
    		att2Label.setText(a2.getName() + " : " + a2.getPower());
    	}else {
    		att2Label.setVisible(false);
    	}
    	if(a3 != null) {
    		att3Label.setVisible(true);
    		att3Label.setText(a3.getName() + " : " + a3.getPower());
    	}else {
    		att3Label.setVisible(false);
    	}
    }

    /**
     * To save the game
     * @param event
     */
    @FXML
    void saveGameMenu(ActionEvent event) {
    	try{
    		model.saveGame();
    	}catch (IOException e){
    		Alert err = new Alert(AlertType.ERROR);
    		err.setContentText("There was an error loading the files");
    		err.showAndWait();
    	}
    }

    /**
     * This method select the current card
     * @param event
     */
    @FXML
    void selectCard(ActionEvent event) {
    	selected = model.getPlayer().getDeck().getPartial().get(currentIndex);
    	battleCardName.setText(selected.getName());
    	Image im = new Image(selected.image);
    	deckCardImage2.setImage(im);
    	Attribute a1 = selected.getAttributes()[0];
    	Attribute a2 = selected.getAttributes()[1];
    	Attribute a3 = selected.getAttributes()[2];
    	battleAtt1Label.setText(a1.getName() + " : " + a1.getPower());
    	if(a2 != null) {
    		battleAtt2Label.setVisible(true);
    		battleAtt2Label.setText(a2.getName() + " : " + a2.getPower());
    	}else {
    		battleAtt2Label.setVisible(false);
    	}
    	if(a3 != null) {
    		battleAtt3Label.setVisible(true);
    		battleAtt3Label.setText(a3.getName() + " : " + a3.getPower());
    	}else {
    		battleAtt3Label.setVisible(false);
    	}
    }
    
    /**
     * This method prepare everything for the battle
     */
    void setupFight() {
    	Random rnd = new Random();
    	String att = attributes.get(rnd.nextInt(attributes.size())).getName();
    	battledAttribute = att;
    	battleAttributeLabel.setText("Battle with Attribute: " + att);
    	enemy = model.getDeck().getRandomCard();
    	displayEnemyStats();
    }
    
    /**
     * This method display the enemy statistics such a power and other stuff
     */
    void displayEnemyStats() {
    	enemyCardName.setText(enemy.getName());
    	Image im = new Image(enemy.image);
    	battleCardImage.setImage(im);
    	Attribute a1 = enemy.getAttributes()[0];
    	Attribute a2 = enemy.getAttributes()[1];
    	Attribute a3 = enemy.getAttributes()[2];
    	enemyAtt1Label.setText(a1.getName() + " : " + a1.getPower());
    	if(a2 != null) {
    		enemyAtt2Label.setVisible(true);
    		enemyAtt2Label.setText(a2.getName() + " : " + a2.getPower());
    	}else {
    		enemyAtt2Label.setVisible(false);
    	}
    	if(a3 != null) {
    		enemyAtt3Label.setVisible(true);
    		enemyAtt3Label.setText(a3.getName() + " : " + a3.getPower());
    	}else {
    		enemyAtt3Label.setVisible(false);
    	}
    }
}