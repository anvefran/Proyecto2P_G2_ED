package com.mycompany.proyectoed2_g2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class MainMenuController {
    
    @FXML
    private ComboBox<Character> playerSymbolBox;
    
    @FXML
    private ComboBox<Character> playerFirstBox;
    
    //info
    public static Character playerSymbol = ' ';
    public static Character aiSymbol = ' ';
    public static Character firstSymbol = ' ';
    
    @FXML
    private void initialize() {
   	
   	ObservableList<Character> items = FXCollections.observableArrayList('X', 'O');
   	playerSymbolBox.setItems(items);
        playerFirstBox.setItems(items);
        
    }
    
    @FXML
    void switchToGame(ActionEvent event) {
        /*Verificacion si los campos son nulos*/
        if(playerSymbolBox.getValue()!=null && playerFirstBox.getValue() != null){
            playerSymbol = playerSymbolBox.getValue();
            setOppositeSymbol(playerSymbol);
            firstSymbol = playerFirstBox.getValue();
            
            //se hace el cambio de escena
            App.switchScenes(event, "Game", 800, 600);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Complete todos los campos para iniciar partida");
            //System.out.println("Complete todos los campos para iniciar partida");
        }
        
        
    }
    
    public void setOppositeSymbol(Character symbol){
        if(symbol.equals('X')){
            aiSymbol = 'O';
        } else {
            aiSymbol = 'X';
        }
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Informacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
