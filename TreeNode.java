package tree;
import java.util.PriorityQueue;

public class TreeNode<E> {
  private E content;
  private PriorityQueue<Tree<E>> children;
  
  public TreeNode(E content){
    this.content = content;
  }
  public E getContent(){
    return this.content;
  }
  public PriorityQueue<Tree<E>> getChildren(){
    return this.children;
  }
  public void setChildren(PriorityQueue<Tree<E>> children){
    this.children = children;
  }
  public void setContent(E content){
    this.content = content;
  }


}