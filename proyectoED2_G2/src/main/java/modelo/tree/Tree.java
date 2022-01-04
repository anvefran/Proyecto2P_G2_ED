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
  

  
}
