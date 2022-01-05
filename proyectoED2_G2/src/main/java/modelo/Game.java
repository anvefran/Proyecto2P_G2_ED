/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import modelo.tree.Tree;
import modelo.tree.TreeNode;

/**
 *
 * @author Usuario
 */
import java.util.PriorityQueue;
import java.util.Random;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Game {

  //simbolo es el del que le toca el turno, en este caso, la computadora
  public static Tree<Tablero> generarPosiblesEstados(Tablero tb, Character simbolo){
    Tree<Tablero> tree = new Tree(new TreeNode(tb)); 
    Character[][] matrix = tb.getMatrix();
    PriorityQueue<Tree<Tablero>> pq = new PriorityQueue<>((o1,o2)->{return o1.getRoot().getContent().getUtilidad()- (o2.getRoot().getContent().getUtilidad());}); //orden por utilidad
    for (int x = 0; x<3; x++){
      for (int y = 0; y<3; y++){
        if (matrix[x][y] != 'X' && matrix[x][y] != 'O'){
          Tablero t = new Tablero();
          t.copiarMatrix(tb.getMatrix());
          t.agregar(x, y, simbolo);
          t.generarUtilidadF(obtenerOpSymbol(simbolo), simbolo);
          pq.offer(new Tree(new TreeNode(t)));
        }
      }
    }
    tree.getRoot().setChildren(pq);
    return tree;
  }

  //genera el arbol y cambia el tablero con el movimiento que se debe hacer
  public static void generarMove(Tablero tb, Character simbolo){
    Tree<Tablero> t1 = generarPosiblesEstados(tb, simbolo);
    PriorityQueue<Tree<Tablero>> pq = t1.getRoot().getChildren();

    if (pq.size()>1){ //para cuando solo quede una opcion de tablero
      PriorityQueue<Tree<Tablero>> pqMayores = new PriorityQueue<>((o1,o2)->{return o2.getRoot().getContent().getUtilidad() - (o1.getRoot().getContent().getUtilidad());});
      while (!pq.isEmpty()){
        Tree<Tablero> t = pq.poll();
        Tree<Tablero> subarbol = generarPosiblesEstados(t.getRoot().getContent(), obtenerOpSymbol(simbolo));
        Tree<Tablero> move = subarbol.getRoot().getChildren().peek();
        int utilidad = move.getRoot().getContent().getUtilidad(); //el de menor utilidad
        t.getRoot().getContent().setUtilidad(utilidad); //se asocia al padre con esa utilidad
        pqMayores.offer(t);
        t.getRoot().setChildren(subarbol.getRoot().getChildren());
      }
      LinkedList<Tablero> lista = new LinkedList<>();
      for (Tree<Tablero> t: pqMayores){
        if (t.getRoot().getContent().compareTo(pqMayores.peek().getRoot().getContent())==0){
          lista.add(t.getRoot().getContent());
        }
      }
      Random r = new Random();
      int index = r.nextInt(lista.size());
      Tablero move = lista.get(index);
      tb.setMatrix(move.getMatrix());
    } //if >1
    else if (pq.size() == 1){
      tb.setMatrix(pq.peek().getRoot().getContent().getMatrix());
    }
  }

  
  public static Character obtenerOpSymbol(Character simboloJug){
    if (simboloJug == 'X'){
      return 'O';
    } else {
      return 'X';
    }
  }

  
  public static Character verifyWinnerRow(Tablero tb){
    boolean conf = false;
    Character simbolo = null;
    int x = 0;
    while (!conf && x<3){
      LinkedList<Character> lista = new LinkedList<>();
      for (int y =0; y<3;y++){
        lista.add(tb.getMatrix()[x][y]);
      }
      Set<Character> s = new HashSet<>();
      s.addAll(lista);
      if (s.size() == 1 && lista.getFirst() != ' '){
        conf = true;
        simbolo = lista.getFirst();
      }
      x++;
    }
    return simbolo;
  }

  public static Character verifyWinnerColumn(Tablero tb){
    boolean conf = false;
    Character simbolo = null;
    int y = 0;
    while (!conf && y<3){
      LinkedList<Character> lista = new LinkedList<>();
      for (int x =0; x<3;x++){
        lista.add(tb.getMatrix()[x][y]);
      }
      Set<Character> s = new HashSet<>();
      s.addAll(lista);
      if (s.size() == 1 && lista.getFirst() != ' '){
        conf = true;
        simbolo = lista.getFirst();
      }
      y++;
    }
    return simbolo;
  }

  public static Character verifyWinnerDiagonal(Tablero tb){
    Character[][] matrix = tb.getMatrix();
    if (matrix[0][2] == matrix[1][1] && matrix[2][0] ==matrix[1][1] && matrix[1][1] != ' '){
      return matrix[1][1];
    }
    if (matrix[1][1] == matrix[0][0] && matrix[1][1] == matrix[2][2] && matrix[1][1] != ' '){
      return matrix[1][1];
    }
    return null;
  }
  
  //dice si hay que seguir jugando o alguien ya gano
  public static boolean continuarJuego(Tablero tb){
    return verifyWinnerRow(tb) == null && verifyWinnerColumn(tb) == null && verifyWinnerDiagonal(tb) == null;
  }

  //retorna el simbolo del que gano
  public static Character obtenerGanador(Tablero tb){
    if (verifyWinnerRow(tb) != null){
      return verifyWinnerRow(tb);
    } else if (verifyWinnerColumn(tb) != null){
      return verifyWinnerColumn(tb);
    } else if (verifyWinnerDiagonal(tb) != null){
      return verifyWinnerDiagonal(tb);
    }
    return null;
  }
  
}