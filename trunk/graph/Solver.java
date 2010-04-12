/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.awt.Dimension;
import java.util.Stack;
import javax.swing.SwingWorker;

/**
 *
 * @author manusoftar
 */
public class Solver extends SwingWorker {
       private cell[][] Maze;
       private MazeGRID padre;
       private Dimension inicio, salida, actual;
       private int alto,ancho;
       private Stack<Dimension> moves = new Stack();
       private Stack<Dimension> backtrace = new Stack();
       private boolean abortar = false;

       private interface walls {
            static final int west = 0;
            static final int east = 1;
            static final int south = 2;
            static final int north = 3;
       }


       public Solver(cell[][] m, Dimension ini, Dimension fin, MazeGRID p){
              padre=p;
              Maze = m.clone();
              alto = Maze.length;
              ancho = Maze[0].length;
              inicio = ini;
              salida = fin;
              //System.out.println(inicio.toString());
             //System.out.println(salida.toString());
       }

       public void abort(){
              abortar=true;
       }
    @Override
    protected Void doInBackground() {
        try {
            actual = new Dimension(inicio.width,inicio.height);
            //System.out.println(actual.toString());
            System.out.println("Inicio: " + inicio.toString());
            System.out.println("Fin: " + salida.toString());
            int n = 0;
            resetVisits();
            Maze[actual.height][actual.width].setVisited();
            int ciclos = 0;
            while (!hasFinished()){
                  if (abortar){
                      break;
                  }
                  
               
                      if (hasVecino(actual.width,actual.height,n)){
                          if (!Maze[actual.height][actual.width].getWall(n)){
                                     if (!Maze[getVecinoY(actual.width,actual.height,n)][getVecinoX(actual.width,actual.height,n)].isVisited()){
                                          moves.push(actual);
                                          Maze[getVecinoY(actual.width,actual.height,n)][getVecinoX(actual.width,actual.height,n)].setVisited();
                                          actual = new Dimension(getVecinoX(actual.width,actual.height,n),getVecinoY(actual.width,actual.height,n));
                                          if (actual.height != salida.height && actual.width != salida.width){
                                              moves.push(actual);
                                          }
                                          ciclos=0;
                                          padre.showSolution(moves);
                                      } else {
                                          n++;
                                          if (n==4){
                                              if (ciclos == 1){
                                                  //backtrace.push(moves.peek());
                                                  actual = moves.pop();
                                                  padre.showSolution(moves);
                                                  ciclos = 0;
                                              }
                                              n=0;
                                              ciclos++;
                                          }
                                      }
                                 //}

                         } else {
                            n++;
                            if (n==4){
                                if (ciclos == 1){
                                    //backtrace.push(moves.peek());
                                    actual = moves.pop();
                                    padre.showSolution(moves);
                                    ciclos = 0;
                                }
                                n=0;
                                ciclos++;
                            }
                         }
                         //padre.showSolution(moves);
                         //System.out.println(actual.toString());
                      } else {
                        n++;
                        if (n==4){
                            if (ciclos == 1){
                                //backtrace.push(moves.peek());
                                actual = moves.pop();
                                padre.showSolution(moves);
                                ciclos = 0;
                            }
                            n=0;
                            ciclos++;
                        }
                    }
            }
            //padre.showSolution(moves);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    private void resetVisits(){
            for (int y=0; y<alto; y++){
                for (int x=0; x<ancho; x++){
                    Maze[y][x].clearVisited();
                }
            }
    }

    private boolean findBacktrace(int x,int y){
            boolean found = false;
            int pos = -1;
            for (int n=0; n<backtrace.size(); n++){
                if (backtrace.get(n).width == x && backtrace.get(n).height == y){
                   found = true;
                }
            }
            return found;
    }

    private boolean isVisited(int x, int y){
            boolean found = false;
            for (int n=0; n<moves.size(); n++){
                if (moves.get(n).width == x && moves.get(n).height == y){
                   found = true;
                }
            }
            return found;
    }



    public void setPadre(MazeGRID p){
        padre = p;
    }


    private boolean hasFinished(){
            if ((actual.height == salida.height) && (actual.width == salida.width)){
               return true;
            } else {
               return false;
            }
    }

    private interface vecinos {
            static final int west = 0;
            static final int east = 1;
            static final int south = 2;
            static final int north = 3;
    }

    public int getVecinoX(int x, int y, int v){
              int retorno=0;
              //if (hasVecino(celda,x,y,v)){
                  switch (v){
                      case vecinos.west:
                           retorno = x-1;
                           break;
                      case vecinos.east:
                           retorno = x+1;
                           break;
                      case vecinos.north:
                           retorno = x;
                           break;
                      case vecinos.south:
                           retorno = x;
                  }
                  return retorno;
              //}
              //return -1;
    }

    public int getVecinoY(int x, int y, int v){
              int retorno = 0;
              //if (hasVecino(celda,x,y,v)){
                  switch(v){
                      case vecinos.south:
                           retorno = y+1;
                           break;
                      case vecinos.north:
                           retorno = y-1;
                           break;
                      case vecinos.west:
                           retorno = y;
                           break;
                      case vecinos.east:
                           retorno = y;
                           break;

                  }
                  return retorno;
              //}

              //return -1;
   }

   public boolean hasVecino(int x, int y, int v){
          if (x>ancho-1 || y>alto-1){
                  return false;
              }
              switch (v){
                  case vecinos.west:
                       if (x-1>=0){
                           return true;
                       }
                       break;
                  case vecinos.east:
                      if (x+1<ancho){
                          return true;
                      }
                      break;
                  case vecinos.north:
                      if (y-1>=0){
                          return true;
                      }
                      break;
                  case vecinos.south:
                      if (y<alto-1){
                          return true;
                      }
                      break;
              }
              return false;
   }
}
