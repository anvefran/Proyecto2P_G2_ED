/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tree;

/**
 *
 * @author Usuario
 */
import java.util.PriorityQueue;


public class TreeNode<E> {
  private E content;
  private PriorityQueue<Tree<E>> children; 
  

  public TreeNode(E content){
    this.content = content;
    this.children = new PriorityQueue<>();
  }
  
  public TreeNode(){
    this.content = null;
    this.children = null;
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
