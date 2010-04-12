/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MazeGRID.java
 *
 * Created on 21/03/2010, 23:20:34
 */

package graph;

import java.awt.geom.Line2D;
import java.util.Stack;
import javax.swing.SwingWorker;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;



/**
 *
 * @author manusoftar
 */
public class MazeGRID extends javax.swing.JPanel {

    /** Creates new form MazeGRID */
    public MazeGRID() {
        initComponents();
        //this.setBackground(Color.black);
        //this.setOpaque(true);
        init();
        //pSupport = new PropertyChangeSupport(this);
        //this.getGraphics().clearRect(alto, alto, alto, ancho);
        //update(this.getGraphics());
    
    }

    public MazeGRID(int x, int y){
           alto=y;
           ancho=x;
           init();
    }
    private int alto=0,ancho=0, celda=4;
    public cell[][] tablero;
    //protected final PropertyChangeSupport pSupport;

    private engine juego_motor;

    private Solver sol_motor;

    private int lastDirection=0;

    private BufferedImage Maze = null;
    private BufferedImage MazeBackup = null;
    private boolean showTail = true, justLoaded=false;
    private Stack<Dimension> jugadas = new Stack();
    private Dimension jugada_actual = new Dimension();

    private Dimension inicio,salida;

    private Stack<Dimension> solution = new Stack();

    private int alg=0;

    private engine motor;
    private int mode = 0;
    private boolean done = false;
    //public Graphics2D g2d=null;
    private Rectangle2D ini,fin;

    private float lineWidth=2.5f;

    private boolean firstTime = true;

    public boolean isFirstTime(){
           return firstTime;
    }

    public void createGraphics(){
        firstTime=false;
    }

    public void setLineWidth(float lw){
        lineWidth = lw;
    }

    void graficar(BufferedImage mazeIMG) {
        Maze = mazeIMG;
    }

    public void showTail(boolean st){
        showTail=st;
    }

    private interface walls {
            static final int west = 0;
            static final int east = 1;
            static final int south = 2;
            static final int north = 3;
    }

    private interface keys {
            static final int arriba = 38;
            static final int derecha = 39;
            static final int abajo = 40;
            static final int izquierda = 37;
    }

    private int delay=0;

    public void init(){
        for (int y=0; y<alto; y++){
             for (int x=0; x<ancho; x++){
                  tablero[y][x]= new cell(true,true,true,true);
             }
        }
    }

    public boolean isBusy(){
           return !done;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @Override
    public void paintComponent(Graphics g) {
           super.paintComponent(g);
           if (alto!=0 && ancho!=0 && celda!=0){
               //this.setBackground(Color.black);
               //g.clearRect(0, 0, ancho*celda, alto*celda);
               Dimension size = super.getSize();
               if (justLoaded){
                   if ((size.height != alto*celda || size.width != ancho*celda)){
                       super.setSize(ancho*celda, alto*celda);
                   }
                   justLoaded=false;
               }
               trabajador worker = new trabajador();
               worker.setAlto(alto);
               worker.setAncho(ancho);
               worker.setCellSize(celda);
               worker.setLineWidth(lineWidth);
               worker.setGraphics(g);
               //worker.setTimeout(0);
               worker.setPadre(this);
               worker.execute();
               while (!worker.isDone()){
               }
               MazeBackup = Maze;
               if (Maze != null){
                   g.drawImage(Maze, 0, 0, this);
               }
           }
           if (done){
               
               
               g.setColor(Color.red);
               ((Graphics2D)g).fill(fin);
               g.setColor(Color.green);
               ((Graphics2D)g).fill(ini);

           }

            if (!solution.isEmpty()){
               //g.setColor(new Color(163,73,164));
               //Rectangle2D mov_act = new Rectangle2D.Double(jugada_actual.getWidth()*celda+lineWidth,jugada_actual.getHeight()*celda+lineWidth,(celda-2*lineWidth),(celda-2*lineWidth));
               //if (!(mov_act.getX()==ini.getX() && mov_act.getY() == ini.getY()) && !(mov_act.getX()==fin.getX() && mov_act.getY() == fin.getY())){
               //    ((Graphics2D)g).fill(mov_act);
               //}
               //if (showTail){
               g.setColor(new Color(163,73,164));
               for (int n=0; n<solution.size(); n++){
               //while (!solution.isEmpty()){
                    try {
                        Rectangle2D mov = new Rectangle2D.Double(solution.get(n).getWidth()*celda+lineWidth,solution.get(n).getHeight()*celda+lineWidth,(celda-2*lineWidth) , (celda-2*lineWidth));
                        if (!(mov.getX()==ini.getX() && mov.getY() == ini.getY())){
                            ((Graphics2D)g).fill(mov);
                        }
                    } catch (Exception ex){
                    }
               }
               g.setColor(Color.LIGHT_GRAY);
           }


           if (!jugadas.isEmpty()){
               g.setColor(new Color(163,73,164));
               Rectangle2D mov_act = new Rectangle2D.Double(jugada_actual.getWidth()*celda+lineWidth,jugada_actual.getHeight()*celda+lineWidth,(celda-2*lineWidth),(celda-2*lineWidth));
               Line2D mov1 = new Line2D.Double();
               double iniy,finy,inix,finx;
               int direction=0;

               if (jugadas.isEmpty()){
                   direction=lastDirection;
               } else {
                   if (jugadas.peek().width > jugada_actual.width){
                       direction=keys.izquierda;
                   }
                   if (jugadas.peek().width < jugada_actual.width){
                       direction=keys.derecha;
                   }
                   if (jugadas.peek().height > jugada_actual.height){
                       direction=keys.abajo;
                   }
                   if (jugadas.peek().height < jugada_actual.height){
                       direction=keys.arriba;
                   }

               }
               
               switch (direction){
                   case keys.abajo:
                        
                        inix = jugada_actual.width*celda+(celda/2)+(lineWidth/2);
                        finy= jugada_actual.height*celda+(celda/2)+(lineWidth/2);
                        iniy= (jugada_actual.height+1)*celda+(celda/2)+(lineWidth/2);
                        mov1 = new Line2D.Double(inix,iniy,inix,finy);
                        break;
                   case keys.arriba:
                        
                        inix = jugada_actual.width*celda+(celda/2)+(lineWidth/2);
                        finy= jugada_actual.height*celda+(celda/2)+(lineWidth/2);
                        iniy= (jugada_actual.height-1)*celda+(celda/2)+(lineWidth/2);
                        mov1 = new Line2D.Double(inix,iniy,inix,finy);
                        break;
                        
                   case keys.derecha:
                        
                        inix = (jugada_actual.width-1)*celda+(celda/2)+(lineWidth/2);
                        finx=  jugada_actual.width*celda+(celda/2)+(lineWidth/2);
                        iniy=  jugada_actual.height*celda+(celda/2)+(lineWidth/2);
                        mov1 = new Line2D.Double(inix,iniy,finx,iniy);
                        break;
                       
                   case keys.izquierda:
                        
                        inix = (jugada_actual.width+1)*celda+(celda/2)+(lineWidth/2);
                        finx=  jugada_actual.width*celda+(celda/2)+(lineWidth/2);
                        iniy= jugada_actual.height*celda+(celda/2)+(lineWidth/2);
                        mov1 = new Line2D.Double(inix,iniy,finx,iniy);
                        break;
                       
                        
               }
               ((Graphics2D)g).setStroke(new BasicStroke(8));
               //if (!(mov_act.getX()==ini.getX() && mov_act.getY() == ini.getY()) && !(mov_act.getX()==fin.getX() && mov_act.getY() == fin.getY())){
                   ((Graphics2D)g).draw(mov1);
               //}
               if (showTail){
                   g.setColor(Color.YELLOW);
                   for (int n=0; n<jugadas.size(); n++){
                        if (n>0){
                            if (jugadas.get(n).width > jugadas.get(n-1).width){
                                direction=keys.derecha;
                            }
                            if (jugadas.get(n).width < jugadas.get(n-1).width){
                                direction=keys.izquierda;
                            }
                            if (jugadas.get(n).height > jugadas.get(n-1).height){
                                direction=keys.arriba;
                            }
                            if (jugadas.get(n).height < jugadas.get(n-1).height){
                                direction=keys.abajo;
                            }
                        } else if (n==0 && jugadas.size()>1) {
                            if (jugadas.get(n+1).width > jugadas.get(n).width){
                                direction=keys.derecha;
                            }
                            if (jugadas.get(n+1).width < jugadas.get(n).width){
                                direction=keys.izquierda;
                            }
                            if (jugadas.get(n+1).height > jugadas.get(n).height){
                                direction=keys.arriba;
                            }
                            if (jugadas.get(n+1).height < jugadas.get(n).height){
                                direction=keys.abajo;
                            }
                        }

                        switch (direction){
                               case keys.abajo:
                                    inix = jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                    if (n!=0){
                                        finy= jugadas.get(n).height*celda+(celda/2)+(lineWidth/2);
                                        iniy= (jugadas.get(n).height+1)*celda+(celda/2)+(lineWidth/2);
                                    } else {
                                        finy= (jugadas.get(n).height+1)*celda+(celda/2)+(lineWidth/2);
                                        iniy= (jugadas.get(n).height+2)*celda+(celda/2)+(lineWidth/2);
                                    }
                                    mov1 = new Line2D.Double(inix,iniy,inix,finy);
                                    break;
                               case keys.arriba:

                                    inix = jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                    if (n!=0){
                                        finy= jugadas.get(n).height*celda+(celda/2)+(lineWidth/2);
                                        iniy= (jugadas.get(n).height-1)*celda+(celda/2)+(lineWidth/2);
                                    } else {
                                        finy= (jugadas.get(n).height-1)*celda+(celda/2)+(lineWidth/2);
                                        iniy= (jugadas.get(n).height-2)*celda+(celda/2)+(lineWidth/2);
                                    }

                                    mov1 = new Line2D.Double(inix,iniy,inix,finy);
                                    break;

                               case keys.derecha:

                                    if (n!=0){
                                        inix= (jugadas.get(n).width-1)*celda+(celda/2)+(lineWidth/2);
                                        finx=  jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                    } else {
                                        inix= jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                        finx=  (jugadas.get(n).width+1)*celda+(celda/2)+(lineWidth/2);
                                    }
                                    iniy=  jugadas.get(n).height*celda+(celda/2)+(lineWidth/2);
                                    mov1 = new Line2D.Double(inix,iniy,finx,iniy);
                                    break;

                               case keys.izquierda:

                                    if (n!=0){
                                        inix = (jugadas.get(n).width+1)*celda+(celda/2)+(lineWidth/2);
                                        finx=  jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                    } else {
                                        inix = jugadas.get(n).width*celda+(celda/2)+(lineWidth/2);
                                        finx=  (jugadas.get(n).width-1)*celda+(celda/2)+(lineWidth/2);
                                    }
                                    iniy= jugadas.get(n).height*celda+(celda/2)+(lineWidth/2);
                                    mov1 = new Line2D.Double(inix,iniy,finx,iniy);
                                    break;


                        }

                         Rectangle2D mov = new Rectangle2D.Double(jugadas.get(n).getWidth()*celda+lineWidth,jugadas.get(n).getHeight()*celda+lineWidth,(celda-2*lineWidth) , (celda-2*lineWidth));
                         
                         //if (!(mov.getX()==ini.getX() && mov.getY() == ini.getY())){
                            ((Graphics2D)g).draw(mov1);
                         //}
                   }

               }
               ((Graphics2D)g).setStroke(new BasicStroke(2));
               g.setColor(Color.LIGHT_GRAY);
           }

           
         
    }

    @Override
    public void update(Graphics g){
        //try {
          
        repaint();
        /*} catch (Exception ex){
              ex.printStackTrace();
        }*/
    }

    public boolean saveMaze(String path){
           try {
               if (path.length() > 0){
                   ObjectOutputStream ost = new ObjectOutputStream( new FileOutputStream(path));
                   ost.writeObject(new MazeObj(ancho,alto,celda,tablero,inicio,salida));
                   ost.close();
                   return true;
               } else {
                   return false;
               }
           } catch (Exception ex){
               ex.printStackTrace();
               return false;
           }
    }

    public void openMaze(String path){
           try {
               if (path.length()>0){
                   ObjectInputStream ois = new ObjectInputStream( new FileInputStream(path));
                   MazeObj mo  =  (MazeObj) ois.readObject();
                   tablero = mo.getBoard().clone();
                   ancho = mo.getWidth();
                   alto = mo.getHeight();
                   celda = mo.getCellSize();
                   inicio = mo.getInit();
                   salida = mo.getFin();
                   setIni(getCornerNum(inicio));
                   setFin(getCornerNum(salida));

                   super.setSize(ancho*celda, alto*celda);
                   ois.close();
                   justLoaded=true;
                   repaint();
               }
           } catch (Exception ex){
               ex.printStackTrace();
           }
    }

    public boolean exportMaze(String path){
           if (path.length()>0){
               String ext = path.substring(path.lastIndexOf(".")+1);
               try {
                   // Save as Imagen
                   System.out.println("Archivo a exportar: " + path + " Extension: " + ext);
                   File file = new File(path);
                   ImageIO.write(Maze, ext, file);
                   return true;
               } catch (Exception ex) {
                   ex.printStackTrace();
                   return false;
               }
           }
           return false;
    }

    public void setAncho(int a){
        ancho=a;
        if (alto!=0){
            tablero = new cell[alto][ancho];
            this.getGraphics().setColor(Color.black);
            this.getGraphics().clearRect(0, 0, ancho, alto);
            repaint();
        }
    }

    public void setAlto(int a){
        alto=a;
        if (ancho!=0){
            tablero = new cell[alto][ancho];
            this.getGraphics().setColor(Color.black);
            this.getGraphics().clearRect(0, 0, ancho, alto);
            repaint();
        }
    }

    public void setCellSize(int cs){
        if (cs>=4){
            celda=cs;
        }
    }

    /**
     * Establece el tiempo en milisegundos que se demorará el pasaje entre casilleros,
     * si se establece en 0 se muestra el laberinto generado instantaneamente.-
     * @param d
     */

    public void setDelay(int d){
           delay = d;
           if (motor != null)
           {
               motor.setDelay(d);
           }
    }

    public void Solve(){
           Solver sol_motor = new Solver(tablero,jugada_actual,salida,this);
           sol_motor.setPadre(this);
           sol_motor.execute();
    }

    public void keyPressed(int keyCode){
           if (done){
               int x,y;
               switch (keyCode){
                   case keys.arriba:
                        y = jugada_actual.height;
                        x = jugada_actual.width;
                        if (y-1>=0 && !tablero[y][jugada_actual.width].getNorth()){
                           if (!jugadas.isEmpty()){
                               Dimension lastPos = jugadas.peek();
                               if (lastPos.height == y-1 ){
                                      jugada_actual=jugadas.pop();
                                      repaint();
                                      break;
                               }
                           }
                           if (showTail) {
                                   jugadas.push(jugada_actual);
                           }
                           y--;
                           jugada_actual = new Dimension(x,y);
                           //lastDirection=keys.arriba;
                           repaint();
                        }
                        break;
                   case keys.abajo:
                       y = jugada_actual.height;
                       x = jugada_actual.width;
                       if (y+1<alto && !tablero[y][x].getSouth()){

                           if (!jugadas.isEmpty()){
                               Dimension lastPos = jugadas.peek();
                               if (lastPos.height == y+1){
                                   jugada_actual=jugadas.pop();
                                   repaint();
                                   break;
                               }
                           }
                           if (showTail){
                               jugadas.push(jugada_actual);
                           }
                           y++;
                           lastDirection=keys.abajo;
                           jugada_actual = new Dimension(x,y);
                           repaint();
                           
                       }
                       break;
                   case keys.derecha:
                       y = jugada_actual.height;
                       x = jugada_actual.width;
                       if (x+1<ancho && !tablero[y][x].getEast()){

                           if (!jugadas.isEmpty()){
                               Dimension lastPos = jugadas.peek();
                               if (lastPos.width == x+1){
                                   jugada_actual=jugadas.pop();
                                   repaint();
                                   break;
                               }
                           }
                           if (showTail){
                               jugadas.push(jugada_actual);
                           }
                           x++;
                           lastDirection=keys.derecha;
                           jugada_actual = new Dimension(x,y);
                           repaint();
                           
                       }
                       break;
                   case keys.izquierda:
                       y = jugada_actual.height;
                       x = jugada_actual.width;
                       if (x-1>=0 && !tablero[y][x].getWest()){

                           if (!jugadas.isEmpty()){
                               Dimension lastPos = jugadas.peek();
                               if (lastPos.width == x-1){
                                   jugada_actual=jugadas.pop();
                                   repaint();
                                   break;
                               }
                           }
                           if (showTail){
                               jugadas.push(jugada_actual);
                           }
                           x--;
                           lastDirection=keys.izquierda;
                           jugada_actual = new Dimension(x,y);
                           repaint();
                           
                       }
                       break;
               }

           }
    }

    public void setAlgorithm(int a){
           if (a==0 || a==1){
               alg = a;
           }
    }

    public void gen(){
           if (Maze != null){
               Maze.getGraphics().setColor(Color.black);
               Maze.getGraphics().clearRect(0, 0, alto*celda, ancho*celda);
               repaint();
               //Maze = null;
           }
           

           System.out.println("Jugada actual --> X=" + jugada_actual.width + " Y=" + jugada_actual.height);

           done = false;
           if (!jugadas.isEmpty()){
               jugadas.clear();
           }

           if (!solution.isEmpty()){
               solution.clear();
           }

           if (sol_motor != null){
               sol_motor.abort();
           }

           motor = new engine(ancho,alto,this);
           motor.setDelay(delay);
           motor.showConstruction((delay!=0));
           motor.setAlg(alg);
           motor.execute();

           int f1 = (int)(Math.random()*4);
           int i1 = (int)(Math.random()*4);
           while (i1==f1){
                i1 = (int)(Math.random()*4);
           }
           setIni(i1);
           setFin(f1);
           inicio=new Dimension(jugada_actual.width,jugada_actual.height);
           /*switch(f1){
                case 0: fin = new Rectangle2D.Double(lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[0][0].clearWALL(walls.north);
                        salida = new Dimension(0,0);
                        break;
                case 1: fin = new Rectangle2D.Double(celda*(ancho-1)+lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[0][ancho-1].clearWALL(walls.north);
                        salida = new Dimension(ancho-1,0);
                        break;
                case 2: fin = new Rectangle2D.Double(lineWidth,celda*(alto - 1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[alto-1][0].clearWALL(walls.south);
                        salida = new Dimension(0,alto-1);
                        break;
                case 3: fin = new Rectangle2D.Double(celda*(ancho-1) + lineWidth,celda*(alto-1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[alto-1][ancho-1].clearWALL(walls.south);
                        salida = new Dimension(ancho-1,alto-1);
                        break;
           }*/

           /*switch(i1){
                case 0: ini = new Rectangle2D.Double(lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(0,0);
                        //tablero[0][0].clearWALL(walls.north);
                        break;
                case 1: ini = new Rectangle2D.Double(celda*(ancho-1)+lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(ancho-1,0);
                        //tablero[0][ancho-1].clearWALL(walls.north);
                        break;
                case 2: ini = new Rectangle2D.Double(lineWidth,celda*(alto - 1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(0,alto-1);
                        //tablero[alto-1][0].clearWALL(walls.south);
                        break;
                case 3: ini = new Rectangle2D.Double(celda*(ancho-1) + lineWidth,celda*(alto-1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(ancho-1,alto-1);
                        //tablero[alto-1][ancho-1].clearWALL(walls.south);
                        break;
           }*/

           //cell(w,e,s,n)

           /**
            * Con esta configuración harcodeada funciona bien
            */
           /*
           tablero[0][0]= new cell(true,false,false,true);
           tablero[0][1]= new cell(false,true,false,true);
           tablero[0][2]= new cell(true,false,false,true);
           tablero[0][3]= new cell(false,false,true,true);
           tablero[0][4]= new cell(false,true,false,true);

           tablero[1][0]= new cell(true,true,false,false);
           tablero[1][1]= new cell(true,true,true,false);
           tablero[1][2]= new cell(true,false,true,false);
           tablero[1][3]= new cell(false,true,false,true);
           tablero[1][4]= new cell(true,true,true,false);

           tablero[2][0]= new cell(true,true,false,false);
           tablero[2][1]= new cell(true,false,false,true);
           tablero[2][2]= new cell(false,false,true,true);
           tablero[2][3]= new cell(false,true,true,false);
           tablero[2][4]= new cell(true,false,false,true);

           tablero[3][0]= new cell(true,true,false,false);
           tablero[3][1]= new cell(true,true,false,false);
           tablero[3][2]= new cell(true,false,false,true);
           tablero[3][3]= new cell(false,false,true,true);
           tablero[3][4]= new cell(false,true,false,false);

           tablero[4][0]= new cell(false,false,true,false);
           tablero[4][1]= new cell(false,false,true,false);
           tablero[4][2]= new cell(false,true,true,false);
           tablero[4][3]= new cell(true,false,true,true);
           tablero[4][4]= new cell(false,true,true,false);
           */
           this.repaint();
    }


    public void setIni(int i1){
        //Rectangle2D ini;
        switch(i1){
                case 0: ini = new Rectangle2D.Double(lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(0,0);
                        //tablero[0][0].clearWALL(walls.north);
                        break;
                case 1: ini = new Rectangle2D.Double(celda*(ancho-1)+lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(ancho-1,0);
                        //tablero[0][ancho-1].clearWALL(walls.north);
                        break;
                case 2: ini = new Rectangle2D.Double(lineWidth,celda*(alto - 1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(0,alto-1);
                        //tablero[alto-1][0].clearWALL(walls.south);
                        break;
                case 3: ini = new Rectangle2D.Double(celda*(ancho-1) + lineWidth,celda*(alto-1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        jugada_actual = new Dimension(ancho-1,alto-1);
                        //tablero[alto-1][ancho-1].clearWALL(walls.south);
                        break;
           }
           //return ini;
    }

    public void setFin(int f1){
           switch(f1){
                case 0: fin = new Rectangle2D.Double(lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[0][0].clearWALL(walls.north);
                        salida = new Dimension(0,0);
                        break;
                case 1: fin = new Rectangle2D.Double(celda*(ancho-1)+lineWidth,lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[0][ancho-1].clearWALL(walls.north);
                        salida = new Dimension(ancho-1,0);
                        break;
                case 2: fin = new Rectangle2D.Double(lineWidth,celda*(alto - 1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[alto-1][0].clearWALL(walls.south);
                        salida = new Dimension(0,alto-1);
                        break;
                case 3: fin = new Rectangle2D.Double(celda*(ancho-1) + lineWidth,celda*(alto-1) + lineWidth,celda-2*lineWidth,celda-2*lineWidth);
                        //tablero[alto-1][ancho-1].clearWALL(walls.south);
                        salida = new Dimension(ancho-1,alto-1);
                        break;
           }
    }

    public int getCornerNum(Dimension corner){
           int num=0;
           if (corner.height == 0 && corner.width == 0){
              num=0;
           } else if (corner.height == 0 && corner.width == ancho - 1) {
              num = 1;
           } else if (corner.height == alto - 1 && corner.width == 0){
              num = 2;
           } else {
              num = 3;
           }

           return num;
    }


    public Color getRandomColor(){
           return new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)) ;
    }

    public void showSolution(Stack<Dimension> movs){
           solution = new Stack();
           solution = movs;
           repaint();
    }

    public void abort(){
           motor.abort();
           done = true;
    }
    
    public void setDone(){
           done=true;
           juego_motor = new engine(tablero);
           //pSupport.firePropertyChange("done", false, true);
           repaint();
    }

    public void draw(cell[][] mapa){
           tablero = mapa.clone();
           this.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_formKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        System.out.println("Tecla presionada: " + evt.getKeyCode());
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
class trabajador extends SwingWorker{
    private MazeGRID padre;
    private int ancho, alto, celda;
    private float lineWidth;
    private Stack<Boolean> paredesh= new Stack(), paredesv = new Stack();
    private BufferedImage mazeIMG;
    private Graphics g;
    

    //private Canvas c;
    //public Graphics2D g2d = null;

    @Override
    protected BufferedImage doInBackground() {
        if (alto!=0 && ancho!=0 && celda!=0 & padre!=null){
               //Graphics2D g2d2 = (Graphics2D)padre.getGraphics();
               setParedes();
               int cnt=0;
               Line2D l2d;

               //if (padre.isFirstTime()){
               mazeIMG=(BufferedImage)padre.createImage(padre.getWidth(), padre.getHeight());
               Graphics2D  g2d = (Graphics2D)mazeIMG.createGraphics();
                   //padre.createGraphics();
              // }
               
               g2d.setBackground(Color.black);
               //padre.setBackground(Color.black);
               g2d.setColor(Color.black);
               //if (padre.isFirstTime()){
               g2d.clearRect(0, 0, ancho*celda, alto*celda);
                   //padre.createGraphics();
               //}
               g2d.setColor(Color.lightGray);
               g2d.setStroke(new BasicStroke(lineWidth));
               for (int y=0; y<=alto*celda; y+=celda){
                   for (int x=0; x<ancho*celda; x+=celda){
                       //g2d.setColor(getRandomColor());
                       l2d = new Line2D.Double(x, y, x+celda, y);
                       //if (padre.tablero[y][x].getNorth()){
                       //System.out.println("Pared h:"+paredesh.peek());
                       if (paredesh.pop()){
                           g2d.draw(l2d);
                           cnt++;
                       }
                       //}
                       
                   }
               }
               g2d.setStroke(new BasicStroke(lineWidth));
               for (int y=0; y<alto*celda; y+=celda){
                   for (int x=0; x<=ancho*celda; x+=celda){
                        //g2d.setColor(getRandomColor());
                        //g2d.drawLine(y, x, y, x+celda);
                        l2d = new Line2D.Double(x, y, x, y+celda);
                        //System.out.println("Pared v:"+paredesv.peek());
                        if (paredesv.pop()){
                            g2d.draw(l2d);
                            //g2d.drawString(String.valueOf(cnt), x+(celda/2), y+(celda/2));
                            cnt++;
                        }
                        
                   }
               }
               //((Graphics2D)g).drawImage(mazeIMG, 0, 0, padre);
               padre.graficar(mazeIMG);
               g2d.dispose();
               //g2d2.drawImage(mazeIMG, 0, 0, padre);
               //System.out.println("Dibujé " + cnt + " líneas");
        }
        
        return mazeIMG;
    }

    

    public void setAlto(int a){
            alto=a;
    }

    public void setAncho(int a){
            ancho=a;
    }

    public void setCellSize(int cs){
            celda=cs;
    }

    public void setPadre(MazeGRID mg){
            padre=mg;
    }

    public void setGraphics(Graphics gr){
        g = gr;
    }

    public void setLineWidth(float lw) {
           lineWidth = lw;
    }


    /*
    public void setTimeout(int to){
        timeout=to;
    }*/

    public void setParedes(){
           paredesh.clear();
           paredesv.clear();
           Stack<Boolean> ph = new Stack(), pv = new Stack();
           //ph.push(true);
           //pv.push(true);
           int x,y;
           for (y=0; y<alto; y++){
               for (x=0; x<ancho; x++){
                   //if (!(y==0 || y<alto-1)){
                       //System.out.println("Seteando paredes...");
                       //ph.push(padre.tablero[y][x].getEast());
                       ph.push(padre.tablero[y][x].getNorth());
                       
                   //} else {
                   //    ph.push(true);
                   //}
               }
               //ph.push(true);
           }
           for (x=0; x<ancho; x++){
                ph.push(true);    
           }

           for (y=0; y<alto; y++){
               pv.push(true);
               for (x=0; x<ancho-1; x++){
                   pv.push(padre.tablero[y][x].getEast());
                   
               }
               pv.push(true);
           }

           paredesh = (Stack<Boolean>) ph.clone();
           paredesv = (Stack<Boolean>) pv.clone();
           while (!ph.isEmpty()){
                 paredesh.push(ph.pop());
           }
           while (!pv.isEmpty()){
               paredesv.push(pv.pop());
           }
           //System.out.println(paredesv.size()+ " " +paredesh.size());

    }
   

}


