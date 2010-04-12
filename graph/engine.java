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
public class engine extends SwingWorker {
       private int cells, ancho=0, alto=0;
       private Stack<Dimension> celdas = new Stack();
       private cell[][] casilleros;
       private Dimension celda = new Dimension();
       private MazeGRID padre;
       private boolean showConstruction=false;
       private int delay=200;
       private boolean abortar=false;
       private boolean debuging=false;

       private interface vecinos {
            static final int west = 0;
            static final int east = 1;
            static final int south = 2;
            static final int north = 3;
       }

       private interface algs {
           static final int DFS = 0;
           static final int CS = 1;
       }

       private int alg=0;

       public void abort(){
              abortar=true;
       }

       public void setAlg(int n){
              if (n>=0 && n<=1){
                  alg = n;
              }
       }

       public engine(int an, int al, MazeGRID p){
           cells=al*an;
           ancho=an;
           alto=al;
           casilleros = new cell[alto][ancho];
           padre = p;
       }

       public engine(cell[][] juego){
           casilleros = juego;
       }

       public engine(){
       }

       public void setPadre(MazeGRID p){
           padre=p;
       }

       public void showConstruction(boolean s){
           showConstruction=s;
       }

       /**
        * Setea la demora entre celda y celda en milisegundos.-
        * @param d
        */
       public void setDelay(int d){
           delay=d;
       }

       public void setCellCount(int c){
           cells=c;
       }

       public void setAncho(int a){
            ancho=a;
            if (alto!=0){
                casilleros = new cell[alto][ancho];
                cells=alto*ancho;
            }
       }

       public void setAlto(int a){
           alto=a;
           if (ancho!=0){
               casilleros = new cell[alto][ancho];
               cells=alto*ancho;
           }
       }

       public int getVecinosCount(int x, int y){
              int cnt=0;
              if (x>0){
                  cnt++;
              }
              if (x+1<ancho){
                  cnt++;
              }
              if (y<alto-1){
                  cnt++;
              }
              if (y>0){
                  cnt++;
              }
              return cnt;
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

       public int getOpossingWall(int w){
              switch(w){
                  case vecinos.east:
                       return vecinos.west;
                  case vecinos.west:
                       return vecinos.east;
                  case vecinos.north:
                       return vecinos.south;
                  case vecinos.south:
                      return vecinos.north;

              }
              return -1;
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

       public int countVecinosLibres(int x, int y){
              int cnt=0;
              for (int n=0; n<4; n++){
                  int xv,yv;
                  xv=getVecinoX(x,y,n);
                  yv=getVecinoY(x,y,n);
                  if (hasVecino(x,y,n)){
                      if (!casilleros[yv][xv].isVisited()){
                          cnt++;
                      }
                  }

              }
              return cnt;
       }

       public int getCellNumber(int x, int y){
              return ancho*y + x + 1;
       }

       @Override
       public Void doInBackground() {
              if (alg==0){
                  int cnt=cells-1;
                  init(0);
                  int x=0,y=0,x1=0,y1=0;
                  x = (int)(Math.random()*ancho);
                  y = (int)(Math.random()*alto);
                  celda = new Dimension(x,y);
                  celdas.push(celda);
                  casilleros[y][x].setVisited();
                  try {
                  while (cnt>0 && !abortar){

                      if (debuging){
                          System.out.println("Celda X:" + x + " Celda Y: " + y + " Celda Nº: " + getCellNumber(x,y));
                      }
                  
                          int cnt3=0;
                          for (int c=0; c<4; c++){
                              if (debuging){
                                  System.out.print("Vecino X: " + getVecinoX(x,y,c) + " Vecino Y:" + getVecinoY(x,y,c) + " Vecino Nº:" + getCellNumber(getVecinoX(x,y,c),getVecinoY(x,y,c)));
                              }
                              if (hasVecino(x,y,c)){
                                  if (debuging){
                                      System.out.print(" OK");
                                  }
                                  if (!casilleros[getVecinoY(x,y,c)][getVecinoX(x,y,c)].isVisited()){
                                      if (debuging){
                                          System.out.println(" Disponible");
                                      }
                                      cnt3++;
                                  } else {
                                      if (debuging){
                                          System.out.println(" Visitado");
                                      }
                                  }
                              } else {
                                if (debuging){
                                    System.out.println(" No tiene este vecino");
                                }
                              }
                          }
                          if (cnt3>=1){
                              int n = (int)(Math.random()*4);
                              //int n = vecinos.north;
                              casilleros[y][x].setVisited();
                              boolean done=false;
                              while (!done){
                                  /*int a = (int)(Math.random()*1000);
                                  if (a%6==0){
                                      if (hasVecino(x,y,vecinos.south)){
                                          if (!casilleros[getVecinoY(x,y,vecinos.south)][getVecinoX(x,y,vecinos.south)].isVisited()){
                                                x1=getVecinoX(x,y,vecinos.south);
                                                y1=getVecinoY(x,y,vecinos.south);
                                                n = vecinos.south;
                                                done=true;
                                                break;
                                             }
                                      }
                                  }*/
                                  if (hasVecino(x,y,n)){
                                         if (!casilleros[getVecinoY(x,y,n)][getVecinoX(x,y,n)].isVisited()){
                                            x1=getVecinoX(x,y,n);
                                            y1=getVecinoY(x,y,n);
                                            done=true;
                                            break;
                                         }

                                  }
                                  n = (int)(Math.random()*4);
                              }

                              casilleros[y][x].clearWALL(n);
                              //x=x1;
                              //y=y1;
                              if (debuging){
                                  System.out.println("Elegí el vecino X:" + x1 + " Y:"+y1 + " Nº:" + getCellNumber(x1,y1));
                              }
                              casilleros[y1][x1].clearWALL(getOpossingWall(n));
                              celda = new Dimension(x1, y1);
                              celdas.push(celda);
                              cnt--;
                              x=x1;
                              y=y1;
                              if (showConstruction){
                                  padre.draw(casilleros);
                                  if (delay>0){
                                      Thread.sleep(delay);
                                  }
                              }
                              if (debuging){
                                  System.out.println("Celdas restantes: " + cnt);
                              }
                          } else {
                              celda = new Dimension(celdas.pop());
                              x = (int)celda.getWidth();
                              y = (int)celda.getHeight();
                          }

                  }
                  if (debuging){
                      System.out.println("Ya generé el laberinto");
                  }
                  padre.draw(casilleros);
                  } catch (Exception ex){
                    if (debuging){
                        System.out.println(ex.toString() + "\n" + ex.getCause());
                        ex.printStackTrace();
                    }
                  }
                  padre.setDone();



              } else if (alg == 1){



                  int cnt=cells-1;
                  init(0);
                  int x=0,y=0,x1=0,y1=0;
                  x = (int)(Math.random()*ancho);
                  y = (int)(Math.random()*alto);
                  celda = new Dimension(x,y);
                  celdas.push(celda);
                  casilleros[y][x].setVisited();
                  try {
                  while (cnt>0 && !abortar){

                      if (debuging){
                          System.out.println("Celda X:" + x + " Celda Y: " + y + " Celda Nº: " + getCellNumber(x,y));
                      }

                          int cnt3=0;
                          for (int c=0; c<4; c++){
                              if (debuging){
                                  System.out.print("Vecino X: " + getVecinoX(x,y,c) + " Vecino Y:" + getVecinoY(x,y,c) + " Vecino Nº:" + getCellNumber(getVecinoX(x,y,c),getVecinoY(x,y,c)));
                              }
                              if (hasVecino(x,y,c)){
                                  if (debuging){
                                      System.out.print(" OK");
                                  }
                                  if (!casilleros[getVecinoY(x,y,c)][getVecinoX(x,y,c)].isVisited()){
                                      if (debuging){
                                          System.out.println(" Disponible");
                                      }
                                      cnt3++;
                                  } else {
                                      if (debuging){
                                          System.out.println(" Visitado");
                                      }
                                  }
                              } else {
                                if (debuging){
                                    System.out.println(" No tiene este vecino");
                                }
                              }
                          }
                          if (cnt3>=1){
                              int n = (int)(Math.random()*4);
                              //int n = vecinos.north;
                              //int n = 0;
                              casilleros[y][x].setVisited();
                              boolean done=false;
                              while (!done){
                                  /*int a = (int)(Math.random()*135489);
                                  if (a%17==0){
                                      if (hasVecino(x,y,vecinos.south)){
                                          if (!casilleros[getVecinoY(x,y,vecinos.south)][getVecinoX(x,y,vecinos.south)].isVisited()){
                                                x1=getVecinoX(x,y,vecinos.south);
                                                y1=getVecinoY(x,y,vecinos.south);
                                                n = vecinos.south;
                                                done=true;
                                                break;
                                             }
                                      }
                                  } else if (a%53==0){
                                     if (hasVecino(x,y,vecinos.west)){
                                          if (!casilleros[getVecinoY(x,y,vecinos.west)][getVecinoX(x,y,vecinos.west)].isVisited()){
                                                x1=getVecinoX(x,y,vecinos.west);
                                                y1=getVecinoY(x,y,vecinos.west);
                                                n = vecinos.west;
                                                done=true;
                                                break;
                                             }
                                      }
                                  }*/
                                  if (hasVecino(x,y,n)){
                                         if (!casilleros[getVecinoY(x,y,n)][getVecinoX(x,y,n)].isVisited()){
                                            x1=getVecinoX(x,y,n);
                                            y1=getVecinoY(x,y,n);
                                            done=true;
                                            break;
                                         }

                                  }
                                  n++;
                                  if (n==4){
                                      n=(int)(Math.random()*4);;
                                  }
                              }

                              casilleros[y][x].clearWALL(n);
                              //x=x1;
                              //y=y1;
                              if (debuging){
                                  System.out.println("Elegí el vecino X:" + x1 + " Y:"+y1 + " Nº:" + getCellNumber(x1,y1));
                              }
                              casilleros[y1][x1].clearWALL(getOpossingWall(n));
                              celda = new Dimension(x1, y1);
                              celdas.push(celda);
                              cnt--;
                              x=x1;
                              y=y1;
                              if (showConstruction){
                                  padre.draw(casilleros);
                                  if (delay>0){
                                      Thread.sleep(delay);
                                  }
                              }
                              if (debuging){
                                  System.out.println("Celdas restantes: " + cnt);
                              }
                          } else {
                              celda = new Dimension(celdas.pop());
                              x = (int)celda.getWidth();
                              y = (int)celda.getHeight();
                          }

                  }
                  if (debuging){
                      System.out.println("Ya generé el laberinto");
                  }
                  padre.draw(casilleros);
                  } catch (Exception ex){
                    if (debuging){
                        System.out.println(ex.toString() + "\n" + ex.getCause());
                        ex.printStackTrace();
                    }
                  }
                  padre.setDone();
              }
              return null;
       }

       public void setDebuging(boolean d){
              debuging=d;
       }

       public void init(int mode){
              if (mode == 0){
                  for (int y=0; y<alto; y++){
                      for (int x=0; x<ancho; x++){
                          casilleros[y][x]= new cell();
                      }
                  }
              } else if (mode == 1){
                  for (int y=0; y<alto; y++){
                      for (int x=0; x<ancho; x++){
                          if (y==0){
                              casilleros[y][x]= new cell(false,false,false,true);
                          } else if (y == alto -1){
                              casilleros[y][x]= new cell(false,false,true,false);
                          } else if (x == 0){
                              casilleros[y][x]= new cell(true,false,false,false);
                          } else if (x == ancho -1){
                              casilleros[y][x]= new cell(false,true,false,false);
                          } else {
                              casilleros[y][x]= new cell(true,true,true,true);
                          }
                      }
                  }
              }
       }

       private boolean findIn(Stack<Integer> st, int n){
               for (int e=0; e<st.size(); e++){
                   if (st.get(e)==n){
                       return true;
                   }
               }
               return false;
       }
}
