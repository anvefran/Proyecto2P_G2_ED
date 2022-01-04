/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * CLASE DE PRUEBAS POR CONSOLA
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
    Tablero tb = new Tablero();
    //EL USUARIO ES X Y LA COMPU ES O
    //tb.agregar(0,0,'X');
    tb.print();
    System.out.println("******************");

    //Scanner sc = new Scanner(System.in);
    //System.out.print("Ingrese su Simbolo (X/O): ");
    //Character usrSimbolo = sc.nextLine().charAt(0); //este se obtiene el menu desplegable en javafx
    //System.out.print("Ingrese quien empieza el juego (Jugador/Computadora): "); //tambien con javafx

    //PRUEBA INICIA COMPUTADORA
    //Game.generarMove(tb, 'O');
    //tb.agregar(0,2,'X');
    
    //tb.agregar(1,0,'O');
    tb.agregar(0,0,'X');
    tb.agregar(1,1,'X');
    tb.agregar(2,2,'X');

    
    
    //tb.agregar(0,1,'O');
    //tb.agregar(2,1,'X');
    //System.out.println(Game.obtenerGanador(tb));
    //System.out.println(Game.verifyWinnerDiagonal(tb));
    //tb.agregar(1,0,'O');
    //tb.agregar(1,1,'X');
    //tb.agregar(1,2,'O');
    //tb.agregar(2,0,'X');
    
    //Game.generarMove(tb, 'O');
    //tb.print();
    //tb.agregar(1,0,'X');
    //Game.generarArbol(tb, 'O');
    tb.print();
  }
}
