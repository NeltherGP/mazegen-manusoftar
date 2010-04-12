/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba1;

import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 *
 * @author manusoftar
 */
public class imgFormats {
// Get list of unique supported read formats
    //String[] formatNames = ImageIO.getReaderFormatNames();
    //formatNames = unique(formatNames);
    // e.g. png jpeg gif jpg
    // Get list of unique supported write formats
   public static void main(String args[]){
    String[] formatNames = ImageIO.getWriterFormatNames();
    formatNames = unique(formatNames);
    for (int n=0; n<formatNames.length; n++){
        System.out.println(formatNames[n]);
    }
   }
    // e.g. png jpeg jpg
    // Get list of unique MIME types that can be read
   // formatNames = ImageIO.getReaderMIMETypes();
   // formatNames = unique(formatNames);
    // e.g image/jpeg image/png image/x-png image/gif
    // Get list of unique MIME types that can be written
   // formatNames = ImageIO.getWriterMIMETypes();
   // formatNames = unique(formatNames);
    // e.g. image/jpeg image/png image/x-png
    // Converts all strings in 'strings' to lowercase
    // and returns an array containing the unique values.
    // All returned values are lowercase.
    public static String[] unique(String[] strings) {
        Set set = new HashSet();
        for (int i=0; i<strings.length; i++) {
            String name = strings[i].toLowerCase();
            set.add(name);
        }
        return (String[])set.toArray(new String[0]);
    }
}
