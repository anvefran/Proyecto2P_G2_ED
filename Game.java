import tree.Tree;
import tree.TreeNode;
import java.util.PriorityQueue;

public class Game {

  //simbolo es el del que le toca el turno, en este caso, la computadora
  public static Tree<Tablero> generarPosiblesEstados(Tablero tb, Character simbolo){
    Tree<Tablero> tree = new Tree(new TreeNode(tb)); 
    Character[][] matrix = tb.getMatrix();
    PriorityQueue<Tree<Tablero>> pq = new PriorityQueue<>((o1,o2)->{return o1.getRoot().getContent().compareTo(o2.getRoot().getContent());}); //orden por utilidad
    
    for (int x = 0; x<3; x++){
      for (int y = 0; y<3; y++){
        if (matrix[x][y] != 'X' && matrix[x][y] != 'O'){
          Tablero t = new Tablero(matrix);
          t.agregar(x,y,simbolo);
          pq.offer(new Tree(new TreeNode(t)));
        }
      }
    }
    tree.getRoot().setChildren(pq);
    
    return tree;
  }

  public static Tree<Tablero> generarArbol(Tablero tb, Character simbolo){
    Tree t1 = generarPosiblesEstados(tb, simbolo);
    PriorityQueue<Tree<Tablero>> pq = t1.getRoot().getChildren();

    while (!pq.isEmpty()){
      Tablero tablero = pq.poll().getRoot().getContent();
      Tree<Tablero> subarbol = generarPosiblesEstados(tablero, obtenerOpSymbol(simbolo));
      t1.getRoot().getChildren().offer(subarbol); 
    }
    return t1;
  }

  public static Character obtenerOpSymbol(Character simboloJug){
    if (simboloJug == 'X'){
      return 'O';
    } else {
      return 'X';
    }
  }

  
}