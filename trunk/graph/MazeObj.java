/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.awt.Dimension;
import java.io.Serializable;

/**
 *
 * @author manusoftar
 */
public class MazeObj implements Serializable {
       private int ancho,alto,cellsize=15;
       private cell[][] tablero;

       private Dimension inicio, fin;

       public MazeObj(){

       }

       /**
        * Crea el objeto Maze con todos sus parámetros asignados.
        * 
        * @param w
        * @param h
        * @param cs
        * @param tbl
        * @param i
        * @param f
        */
       public MazeObj(int w, int h, int cs, cell[][] tbl, Dimension i, Dimension f){
              alto = h;
              ancho = w;
              cellsize = cs;
              tablero = tbl.clone();
              inicio = i;
              fin = f;
       }


       public void setTablero(cell[][] tbl){
              tablero = tbl.clone();
       }

       public void setDimensiones(int w, int h){
              ancho = w;
              alto = h;
       }

       public void setCellSize(int cs){
              cellsize=cs;
       }

       public int getHeight(){
              return alto;
       }

       public int getWidth(){
              return ancho;
       }

       public int getCellSize(){
            return cellsize;
       }

       public cell[][] getBoard(){
              return tablero;
       }

       public void setInitFin(Dimension i, Dimension f){
              inicio = i;
              fin = f;
       }

       public Dimension getInit(){
            return inicio;
       }

       public Dimension getFin(){
            return fin;
       }

}
