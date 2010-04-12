package prueba1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author manusoftar
 */
public class Multiplos {
       public static void main(String args[]){
              int a[] = new int[1000];
              for (int n=1; n<=1000; n++){
                  for (int c=2; c<Math.sqrt(1000); c++){
                      if (n%c==0){
                          a[c-2]++;
                      }
                  }
              }
              for (int n=0; n<a.length; n++){
                  if (a[n]!=0){
                     System.out.println("Multiplos de " + (n+2) + ": " + a[n]);
                  }
              }
       }
       
}
