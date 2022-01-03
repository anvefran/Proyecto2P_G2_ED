import tree.Tree;
import tree.TreeNode;
import java.util.PriorityQueue;

class Main {
  public static void main(String[] args) {
    Tablero tb = new Tablero();
    //EL USUARIO ES X Y LA COMPU ES O
    //tb.agregar(0,0,'X');
    tb.print();
    System.out.println("******************");
    //tb.agregar(1,0,'O');
    //tb.agregar(0,1,'O');
    //tb.agregar(0,2,'X');
    //tb.agregar(1,0,'O');
    //tb.agregar(1,1,'X');
    //tb.agregar(1,2,'O');
    //tb.agregar(2,0,'X');
    
    Game.generarArbol(tb, 'O');
    //tb.print();
    //tb.agregar(1,0,'X');
    //Game.generarArbol(tb, 'O');
    //tb.print();
  }
}