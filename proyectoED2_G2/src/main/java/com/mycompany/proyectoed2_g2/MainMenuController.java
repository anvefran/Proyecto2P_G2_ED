package com.mycompany.proyectoed2_g2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class MainMenuController {
    
    @FXML
    private ComboBox<String> playerSymbol;
    
    @FXML
    private ComboBox<String> playerFirst;
    
    @FXML
    private void initialize() {
   	
   	ObservableList<String> items = FXCollections.observableArrayList("X", "O");
   	playerSymbol.setItems(items);
        playerFirst.setItems(items);
        
    }
    
    @FXML
    void switchToGame(ActionEvent event) {
        App.switchScenes(event, "Game", 800, 600);
    }
    
    

}
