import tree.Tree;
import tree.TreeNode;
import java.util.PriorityQueue;

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
  public static void generarArbol(Tablero tb, Character simbolo){
    Tree<Tablero> t1 = generarPosiblesEstados(tb, simbolo);
    PriorityQueue<Tree<Tablero>> pq = t1.getRoot().getChildren();
    PriorityQueue<Tree<Tablero>> pqMayores = new PriorityQueue<>((o1,o2)->{return o2.getRoot().getContent().getUtilidad() - (o1.getRoot().getContent().getUtilidad());});
    //if (pq.size()>1){ //para cuando solo quede una opcion de tablero
    for (Tree<Tablero> t: pq){
      Tree<Tablero> subarbol = generarPosiblesEstados(t.getRoot().getContent(), obtenerOpSymbol(simbolo));
      Tree<Tablero> move = subarbol.getRoot().getChildren().peek();
      int utilidad = move.getRoot().getContent().getUtilidad(); //el de menor utilidad
      t.getRoot().getContent().setUtilidad(utilidad); //se asocia al padre con esa utilidad
      pqMayores.offer(t);
      
    }
    //}
    /* prueba 
    for (Tree<Tablero> t: pqMayores){
      System.out.println(t.getRoot().getContent().getUtilidad());
    }
    System.out.println(pqMayores.peek().getRoot().getContent().getUtilidad());
    */
    tb.setMatrix(pqMayores.peek().getRoot().getContent().getMatrix());
  }

  /*
  public static Tree<Tablero> generarArbol(Tablero tb, Character simbolo){
    Tree<Tablero> t1 = generarPosiblesEstados(tb, simbolo);
    PriorityQueue<Tree<Tablero>> pq = t1.getRoot().getChildren();

    while (!pq.isEmpty()){
      Tablero tablero = pq.poll().getRoot().getContent();
      Tree<Tablero> subarbol = generarPosiblesEstados(tablero, obtenerOpSymbol(simbolo));
      t1.getRoot().getChildren().offer(subarbol); 
    }
    return t1;
  }
  */
  public static Character obtenerOpSymbol(Character simboloJug){
    if (simboloJug == 'X'){
      return 'O';
    } else {
      return 'X';
    }
  }

  /*
  //este metodo se usa si se necesita el tablero para la interfaz, por ejemplo, se podria limpiar y mostrar el nuevo tablero
  public Tablero obtenerMove(Tablero tb, Character simbolo){
    Tree<Tablero> tree = generarArbol(tb,simbolo);
    PriorityQueue<Tree<Tablero>> pq = tree.getRoot().getChildren();
    PriorityQueue<Tree<Tablero>> pqMayores = new PriorityQueue<>((o1,o2)->{return o2.getRoot().getContent().compareTo(o1.getRoot().getContent());});
    
    while(!pq.isEmpty()){
      Tree<Tablero> t = pq.poll(); 
      int utilidad = t.getRoot().getChildren().peek().getRoot().getContent().getUtilidad(); //el de menor utilidad
      t.getRoot().getContent().setUtilidad(utilidad); //se asocia al padre con esa utilidad
      pqMayores.offer(t);
    }
    return pqMayores.peek().getRoot().getContent();
  }
  */

  /*
  //este metodo de una vez modifica el tablero que le fue enviado 
  public static void obtenerMove(Tablero tb, Character simbolo){
    Tree<Tablero> tree = generarArbol(tb,simbolo);
    PriorityQueue<Tree<Tablero>> pq = tree.getRoot().getChildren();
    PriorityQueue<Tree<Tablero>> pqMayores = new PriorityQueue<>((o1,o2)->{return o2.getRoot().getContent().compareTo(o1.getRoot().getContent());});
    
    while(!pq.isEmpty()){
      Tree<Tablero> t = pq.poll(); 
      int utilidad = t.getRoot().getChildren().peek().getRoot().getContent().getUtilidad(); //el de menor utilidad
      t.getRoot().getContent().setUtilidad(utilidad); //se asocia al padre con esa utilidad
      pqMayores.offer(t);
    }
    tb = pqMayores.peek().getRoot().getContent();
  }
  */

  
}
