/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.io.Serializable;

/**
 *
 * @author manusoftar
 */
public class cell implements Serializable {
      private boolean east=true, west=true, south=true, north=true, bsouth=false, bnorth=false,
              bwest=false, beast=false, visited=false;

      /**
       * Inicializa la clase especificando las paredes que deben estar levantadas.-
       * 
       * @param w
       * @param e
       * @param s
       * @param n
       */
      public cell(boolean w, boolean e, boolean s, boolean n){
             east=e;
             west=w;
             south=s;
             north=n;
      }

      public cell(){
      }


      public boolean getWall(int n){
             switch (n){
                 case walls.east :
                                 return east;
                 case walls.west:
                                 return west;
                 case walls.north:
                                 return north;
                 case walls.south:
                                 return south;
             }
             return true;
      }

      private interface walls {
            static final int west = 0;
            static final int east = 1;
            static final int south = 2;
            static final int north = 3;
      }

      public void clearWALL(int wall){
             switch(wall){
                 case walls.east:
                        if (!beast){
                            east=false;
                        }
                        break;
                 case walls.west:
                        if (!bwest){
                            west=false;
                        }
                        break;
                 case walls.south:
                        if (!bsouth){
                            south=false;
                        }
                        break;
                 case walls.north:
                        if (!bnorth){
                            north=false;
                        }
                        break;

             }
             visited=true;
      }

      public boolean isClosed(){
             return south && north && west && east;
      }

      public void setWall(int n){
             switch(n){
                 case walls.east:
                      east = true;
                      break;
                 case walls.west:
                      west = true;
                      break;
                 case walls.south:
                      south = true;
                      break;
                 case walls.north:
                      north = true;
                      break;
             }
      }

      public void setBorder(int wall){
             switch(wall){
                    case walls.east:
                           beast=false;
                           break;
                    case walls.west:
                           bwest=false;
                           break;
                    case walls.south:
                           bsouth=false;
                           break;
                    case walls.north:
                           bnorth=false;
                           break;

             }
      }

      public void reset(){
             east=true;
             west=true;
             north=true;
             south=true;
             bsouth=false;
             bnorth=false;
             bwest=false;
             beast=false;
             visited=false;
      }

      public void setVisited(){
          visited=true;
      }

      public void clearVisited(){
          visited=false;
      }

      public boolean getEast(){
             return east;
      }

      public boolean getWest(){
             return west;
      }

      public boolean getSouth(){
             return south;
      }

      public boolean getNorth(){
            return north;
      }

      public boolean isVisited(){
          return visited;
      }
}
