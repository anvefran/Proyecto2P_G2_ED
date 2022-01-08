package com.mycompany.proyectoed2_g2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.Tablero;

public class GameController {
    
    @FXML
    private GridPane gamePane;
    
    private Tablero tableGame;
    
    private Character playerSymbol = MainMenuController.playerSymbol;
    private Character aiSymbol = MainMenuController.aiSymbol;
    private Character firstSymbol = MainMenuController.firstSymbol;
    

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
        
        /*Verifica si la maquina debe empezar primero*/
        if(!firstSymbol.equals(playerSymbol)){
            aiMove();
        }
    }

    private void updateUITable() {
        Character[][] matrix = tableGame.getMatrix();
        
        for(int y = 0; y < matrix.length ; y++){
            for(int x = 0; x < matrix[y].length ; x++){
                
                Character currentChar = matrix[y][x];
                
                VBox vbox = new VBox();
                vbox.setPrefSize(100, 100);
                vbox.setAlignment(Pos.CENTER);  
                
                
                loadSymbolImage(currentChar,vbox);    /*Carga la imagen del simbolo*/

                final int indexX = x;
                final int indexY = y;
                
                
                vbox.setOnMouseClicked(e -> {
                    
                    if(tableGame.getMatrix()[indexY][indexX] == ' '){
                        tableGame.getMatrix()[indexY][indexX] =  playerSymbol;
                        updateUITable();  /*Actualiza el GridPane*/
                        //verifyWinner();   /*Verifica si alguien ha ganado*/
                        aiMove();         /*Realiza el movimiento de la maquina*/
                        verifyWinner();   /*Verifica si alguien ha ganado*/
                        
                        
                    } else {
                        /*La posicion no debe estar ocupada por otra ficha*/
                        
                        mostrarAlerta(Alert.AlertType.ERROR, "No puede colocar en una posicion ya ocupada");
                        System.out.println("Ya se encuentra ocupado");
                    }
                    
                });
                gamePane.add(vbox, x, y);
            }
        }
    }
    
    private void aiMove(){
        Game.generarMove(tableGame, aiSymbol);
        updateUITable();
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Informacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void loadSymbolImage(Character currentChar, VBox vbox) {
        if (currentChar == 'X') {
            Image img = new Image("images/x.png", 90, 90, false, false);
            ImageView imv = new ImageView(img);
            vbox.getChildren().add(imv);
        }

        if (currentChar == 'O') {
            Image img = new Image("images/o.png", 100, 100, false, false);
            ImageView imv = new ImageView(img);
            vbox.getChildren().add(imv);

        }
    }
    
    private int verifyWinner(){
        if(!Game.continuarJuego(tableGame)){
            mostrarAlerta(Alert.AlertType.INFORMATION, "Alguien ha ganado");
            volverMenu();
            return 0;
        }
        
        if(isTie()){
            mostrarAlerta(Alert.AlertType.INFORMATION, "Han empatado!");
            volverMenu();
            return 0;
        }
        
        return 1;

    }
    
    private void volverMenu() {
    	try {
            /*Cierra la ventana actual*/
            Stage gameStage = (Stage) gamePane.getScene().getWindow();
            gameStage.close();
            
            /*Carga la pantalla principal*/
            Parent root = App.loadFXML("MainMenu");
            Stage stage  = new Stage();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            
        } catch (IOException e){
            System.out.println("File not found, Error al cargar pantalla");
        }
    }
    
    
    public boolean isTie(){
        
        Character[][] matrix = tableGame.getMatrix();
        for(int i = 0; i<matrix.length ; i++ ){
            for(int j = 0; j<matrix[i].length ; j++){
                if(matrix[i][j] == ' '){
                    return false;
                }
            }
        }
        return true && Game.continuarJuego(tableGame);
    }
}
