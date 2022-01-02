import java.util.*;

public class Tablero implements Comparable<Tablero> {
  private int utilidad;
  private Character[][] matrix;

  public Tablero(Character[][] matrix){
    this.matrix = matrix;
    this.utilidad = 0;
  }
  //iniciliza la matriz con espacios vacios
  public Tablero(){
    this.utilidad = 0;
    for (int x=0; x < matrix.length; x++) {
      for (int y=0; y < matrix[x].length; y++) {
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
  //se envia el simbolo del oponente 
  public int utilidadRows(Character simboloOp){
    int util = 0;
    for (int x = 0; x<3; x++){
      if (verifyRowOrColumn(simboloOp, x, "row")){
        util++;
      }
    }
    return util;
  }

  public int utilidadColumns(Character simboloOp){
    int util = 0;
    for (int x = 0; x<3; x++){
      if (verifyRowOrColumn(simboloOp, x, "column")){
        util++;
      }
    }
    return util;
  }

  //verifica que no haya una celda con el simbolo del oponente
  public boolean verifyRowOrColumn(Character simboloOp, int x, String orientacion){
    if (orientacion == "row"){
      return matrix[x][0] != simboloOp && matrix[x][1] != simboloOp && matrix[x][2] != simboloOp;
    } else {
      return matrix[0][x] != simboloOp && matrix[1][x] != simboloOp && matrix[2][x] != simboloOp;
    }
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

}
