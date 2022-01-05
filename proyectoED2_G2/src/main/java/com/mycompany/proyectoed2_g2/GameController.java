package com.mycompany.proyectoed2_g2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Game;
import modelo.Tablero;

public class GameController {
    
    @FXML
    private GridPane gamePane;
    
    private Tablero tableGame;

    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    @FXML
    private void initialize() {
   	initGame();
        
    }
    
    private void initGame(){
        tableGame = new Tablero();
        updateUITable();
    }

    private void updateUITable() {
        Character[][] matrix = tableGame.getMatrix();
        
        for(int y = 0; y < matrix.length ; y++){
            for(int x = 0; x < matrix[y].length ; x++){
                
                Character currentChar = matrix[y][x];
                
                VBox vbox = new VBox();
                vbox.getChildren().add(new Text(Character.toString(currentChar)));
                vbox.setPrefSize(100, 100);
                vbox.setAlignment(Pos.CENTER);        
                
                final int indexX = x;
                final int indexY = y;
                
                
                vbox.setOnMouseClicked(e -> {
                    tableGame.getMatrix()[indexY][indexX] = 'X';
                    updateUITable();
                    aiMove();
                    updateUITable();
                    tableGame.print();
                    
                    if(!Game.continuarJuego(tableGame)){
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Alguien ha ganado");
                    }
                    
                });
                
                gamePane.add(vbox, x, y);
            }
        }
    }
    
    private void aiMove(){
        Game.generarMove(tableGame, 'O');
        //updateUITable();
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
