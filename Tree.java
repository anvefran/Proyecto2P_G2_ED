package tree;

public class Tree<E>{
  private TreeNode<E> root;

  public Tree(TreeNode<E> root){
    this.root = root;
  }
  public Tree(){ 
    this.root = null;
  }
  public void setRoot(TreeNode<E> root){
    this.root = root;
  }
  public TreeNode<E> getRoot(){
    return root;
  }
  public Tree<E> getChildren(){
    return root.getChildren().peek(); //retorna el de mayor utilidad
  }

  
}