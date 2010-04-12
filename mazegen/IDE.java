/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IDE.java
 *
 * Created on 21/03/2010, 23:20:16
 */

package mazegen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author manusoftar
 */
public class IDE extends javax.swing.JFrame {

    /** Creates new form IDE */
    public IDE() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private boolean generando = false;
    private int ancho=15,alto=15,delay=90,cellsize=10;
    private Config cnf;
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        mazeGRID1 = new graph.MazeGRID();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manusoftar® - MazeGen");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("Generar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mazeGRID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        mazeGRID1.setDoubleBuffered(false);
        mazeGRID1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mazeGRID1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout mazeGRID1Layout = new javax.swing.GroupLayout(mazeGRID1);
        mazeGRID1.setLayout(mazeGRID1Layout);
        mazeGRID1Layout.setHorizontalGroup(
            mazeGRID1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        mazeGRID1Layout.setVerticalGroup(
            mazeGRID1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );

        jButton2.setText("Resolver");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem2.setText("Nuevo");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Cargar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Guardar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("SALIR");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        jMenuItem1.setText("Configuración");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Laberinto");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenu4.setText("Generar");

        buttonGroup1.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText("DFS");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem2);

        buttonGroup1.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setText("Crawling Snake");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.getAccessibleContext().setAccessibleName("jRadioButtonMenuItem2");

        jMenu3.add(jMenu4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Generar nuevo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Abortar generación");
        jMenuItem8.setEnabled(false);
        jMenu3.add(jMenuItem8);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Resolver");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);
        jMenu3.add(jSeparator1);

        jMenuItem9.setText("Exportar");
        jMenuItem9.setToolTipText("Exportar el laberinto igual como imagen");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mazeGRID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mazeGRID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private interface keys {
            static final int arriba = 38;
            static final int derecha = 39;
            static final int abajo = 40;
            static final int izquierda = 37;
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        mazeGRID1.setAlto(15);
        mazeGRID1.setAncho(15);
        mazeGRID1.setCellSize(20);
        mazeGRID1.repaint();
/*        jSpinner1.setFocusable(false);
        jSpinner2.setFocusable(false);
        jSpinner3.setFocusable(false);
        jSpinner4.setFocusable(false);*/
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        this.pack();
        Timer tm = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   if (generando && !mazeGRID1.isBusy()){
                       //jButton1.setText("Generar");
                       jMenuItem8.setEnabled(false);
                       generando = false;
                   }
                   mazeGRID1.setSize(ancho*cellsize,alto*cellsize);
                   //IDE.this.pack();
            }
        });

        tm.start();
        

        //this.pack();
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jButton1.getText().equalsIgnoreCase("generar")){
            jButton1.setText("Cancelar");
            mazeGRID1.setAncho(ancho);
            mazeGRID1.setAlto(alto);
            mazeGRID1.setCellSize(cellsize);
            mazeGRID1.setDelay(delay);
            mazeGRID1.gen();
            generando = true;
        } else {
            jButton1.setText("Generar");
            generando = false;
            mazeGRID1.abort();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mazeGRID1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mazeGRID1KeyPressed
        // TODO add your handling code here:
        //System.out.println("Tecla presionada: " + evt.getKeyCode());
        
    }//GEN-LAST:event_mazeGRID1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        //System.out.println("Tecla presionada: " + evt.getKeyCode());
        switch (evt.getKeyCode()){
            case keys.arriba:
                                mazeGRID1.keyPressed(keys.arriba);
                                break;
            case keys.derecha:
                                mazeGRID1.keyPressed(keys.derecha);
                                break;
            case keys.abajo:
                                mazeGRID1.keyPressed(keys.abajo);
                                break;
            case keys.izquierda:
                                mazeGRID1.keyPressed(keys.izquierda);
                                break;

        }
    }//GEN-LAST:event_formKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (cnf==null){
            cnf = new Config(ancho,alto,delay,cellsize);
        }
        cnf.setPadre(this);
        cnf.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CustomFileChooser CFS = new CustomFileChooser();
        String path = CFS.saveFile(new CustomFilter("mmf","Manusoftar® Maze File (*.mmf)"));
        if (mazeGRID1.saveMaze(path)){
            System.out.println("Archivo guardado!");
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CustomFileChooser CFS = new CustomFileChooser();
        String path = CFS.openFile(new CustomFilter("mmf","Manusoftar® Maze File (*.mmf)"));
        mazeGRID1.openMaze(path);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        String[] formatos = ImageIO.getWriterFormatNames();
        formatos = unique(formatos);
        CustomFileChooser cfc = new CustomFileChooser();
        CustomFilter[] filtros = new CustomFilter[formatos.length];
        for (int n=0; n<filtros.length; n++){
            filtros[n] = new CustomFilter(formatos[n].toUpperCase(),"Archivo de imagen (*." + formatos[n].toLowerCase()+")");
        }
        String expPath = cfc.saveFile(filtros);
        System.out.println(expPath);
        if (mazeGRID1.exportMaze(expPath)){
            System.out.println("Laberinto exportado como imagen exitosamente");
        }

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mazeGRID1.Solve();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonMenuItem2.isSelected()){
            mazeGRID1.setAlgorithm(0);
        }
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonMenuItem1.isSelected()){
            mazeGRID1.setAlgorithm(1);
        }
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
            jMenuItem8.setEnabled(true);
            mazeGRID1.setAncho(ancho);
            mazeGRID1.setAlto(alto);
            mazeGRID1.setCellSize(cellsize);
            mazeGRID1.setDelay(delay);
            mazeGRID1.gen();
            generando = true;
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        mazeGRID1.abort();
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        mazeGRID1.Solve();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    public static String[] unique(String[] strings) {
        Set set = new HashSet();
        for (int i=0; i<strings.length; i++) {
            String name = strings[i].toLowerCase();
            set.add(name);
        }
        return (String[])set.toArray(new String[0]);
    }

    /**
     *
     * @param al
     * @param an
     * @param del
     * @param cell
     */
    public void setDatos(int al, int an, int del, int cell){
           cellsize = cell;
           delay = del;
           ancho = an;
           alto = al;
           mazeGRID1.setDelay(del);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JSeparator jSeparator1;
    private graph.MazeGRID mazeGRID1;
    // End of variables declaration//GEN-END:variables

}

class CustomFileChooser extends JFrame {

      private String Path="";
      //private int mode=0;
      private JFileChooser FileChooser = new JFileChooser();
      private boolean done=false, result;
      
      
      public CustomFileChooser(){
            FileChooser.addActionListener(alistener);
            FileChooser.setAccessory(new ImagePreview(FileChooser));
            this.add(FileChooser);
      }
      
      ActionListener alistener = new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                                if(e.getActionCommand().equals("ApproveSelection")){
                                    result=true;
                                } else {
                                    result=false;
                                }
                                done=true;
                    }

      };

      public String openFile(CustomFilter Filter){
             done=false;
             FileChooser.addChoosableFileFilter(Filter);
             FileChooser.showOpenDialog(this);
             while (!done){}
             if (result){
                return FileChooser.getSelectedFile().getPath();
             } else {
                return "";
             }
      }

      public String openFile(CustomFilter[] Filter){
             done=false;
             for (int n=0; n<Filter.length; n++){
                 FileChooser.addChoosableFileFilter(Filter[n]);
             }
             FileChooser.showOpenDialog(this);
             while (!done){}
             
             if (result){
                return FileChooser.getSelectedFile().getPath();
             } else {
                return "";
             }
      }

      public String saveFile(CustomFilter Filter){
             done=false;
             FileChooser.addChoosableFileFilter(Filter);
             FileChooser.showSaveDialog(this);
             while (!done){}
             if (result){
                 if (FileChooser.getSelectedFile().getPath().indexOf(".")==-1){
                    return FileChooser.getSelectedFile().getPath()+"."+((CustomFilter)FileChooser.getFileFilter()).getMyExt().toLowerCase();
                 } else {
                    return FileChooser.getSelectedFile().getPath();
                 }
             } else {
                return "";
             }
      }

      public String saveFile(CustomFilter[] Filter){
             done=false;
             for (int n=0; n<Filter.length; n++){
                 FileChooser.addChoosableFileFilter(Filter[n]);
             }
             FileChooser.showSaveDialog(this);
             while (!done){}
             //System.out.println("Extensión: " + ((CustomFilter)FileChooser.getFileFilter()).getMyExt());
             if (result){
                if (FileChooser.getSelectedFile().getPath().indexOf(".")==-1){
                   return FileChooser.getSelectedFile().getPath()+"."+((CustomFilter)FileChooser.getFileFilter()).getMyExt().toLowerCase();
                } else {
                   return FileChooser.getSelectedFile().getPath();
                }
             } else {
                return "";
             }
      }

}