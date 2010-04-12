/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mazegen;

/**
 *
 * @author manusoftar
 */
import java.io.File;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class CustomFilter extends FileFilter {

    private String ext = "", desc = "";

    /**
     * Establece la extensión del archivo y la descripción del filtro
     * @param ext
     * @param desc
     */
    public CustomFilter(String et, String dsc){
           ext = et.toUpperCase();
           desc = dsc;
    }

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExt(f);
        if (extension != null) {
            if (extension.equalsIgnoreCase(ext)){
                   return true;
            }
            return false;
        } else {
                return false;
        }
        //return false;
    }

    public String getMyExt(){
           return ext; 
    }

    //The description of this filter
    public String getDescription() {
        return desc;
    }

    public static String getExt(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toUpperCase();
        }
        return ext;
    }



}

