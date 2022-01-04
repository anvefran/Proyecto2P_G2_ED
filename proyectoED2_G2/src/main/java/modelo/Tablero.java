/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */

public class Tablero implements Comparable<Tablero> {
  private int utilidad;
  private Character[][] matrix = new Character[3][3];

  public Tablero(Character[][] matrix){
    this.matrix = matrix;
    this.utilidad = 0;
  }
  //iniciliza la matriz con espacios vacios
  public Tablero(){
    this.utilidad = 0;
    for (int x=0; x < 3; x++) {
      for (int y=0; y < 3; y++) {
        this.matrix[x][y] = ' ';
      }
    }
  }

  public int getUtilidad(){
    return this.utilidad;
  }
  public Character[][] getMatrix(){
    return this.matrix;
  }
  public void setUtilidad(int utilidad){
    this.utilidad = utilidad;
  }
  public void setMatrix(Character[][] matrix){
    this.matrix = matrix;
  }

  public void print(){
    for (int x = 0; x<3; x++){
      for (int y = 0; y<3; y++){
        System.out.print( "| "+ matrix[x][y]+ " |");
        if (y == 2){
          System.out.println();
          //System.out.println("--------------");
        }
      }
    }
  }
  //se envia el simbolo del oponente 
  public int utilidadRows(Character simboloOp){
    int util = 0;
    for (int x = 0; x<3; x++){
      if (matrix[x][0] != simboloOp && matrix[x][1] != simboloOp && matrix[x][2] != simboloOp){
        util++;
      }
    }
    return util;
  }

  public int utilidadColumns(Character simboloOp){
    int util = 0;
    for (int x = 0; x<3; x++){
      if (matrix[0][x] != simboloOp && matrix[1][x] != simboloOp && matrix[2][x] != simboloOp){
        util++;
      }
    }
    return util;
  }

  public int utilidadDiagonal(Character simboloOp){
    int util = 0;
    if (matrix[1][1] == simboloOp){ //verifica el centro
      return 0;
    } 
    if (matrix[0][0] != simboloOp && matrix[2][2] != simboloOp){
      util++;
    }
    if (matrix[0][2] != simboloOp && matrix[2][0] != simboloOp){
      util++;
    }
    return util;
  }

  public int compareTo(Tablero tb){
    return this.utilidad - tb.getUtilidad();
  }

  //se genera la utilidad con respecto al simbolo de la computadora
  public void generarUtilidadF(Character simboloUsr, Character simboloCpu){
    int comp = this.utilidadRows(simboloUsr) + this.utilidadColumns(simboloUsr) + this.utilidadDiagonal(simboloUsr);
    int usr = this.utilidadRows(simboloCpu) + this.utilidadColumns(simboloCpu) +this.utilidadDiagonal(simboloCpu);
    this.setUtilidad(comp-usr);
  }  

  //agrega una jugada
  public void agregar(int x, int y, Character simbolo){
    matrix[x][y] = simbolo;
  }

  public void copiarMatrix(Character[][] arr2){
    Character[][] arr = new Character[3][3];
    for (int x = 0; x<3; x++){
      for (int y = 0; y<3; y++){
        arr[x][y] = arr2[x][y];
      }
    }
    this.setMatrix(arr);
  }

  
}