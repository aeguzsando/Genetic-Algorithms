
package agonemax;

import static agonemax.AGoneMax.tam_genes;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;



public class AGoneMaxV03 {
    
    static int tam_genes = 3;   							
    static int tam_poblacion =4; 
    static int generaciones = 10; 
    static List<List<Integer> > poblacion = new ArrayList<>();
    
    public static void main(String[] args){
        
        iniciarPoblacion();
        
        for (int i = 0; i < generaciones; i++) {
            int i_padre1 = 0;
            int i_padre2 = 0;
            int score_total = 0;
            
            for (int j = 0; j < tam_poblacion; j++) {
                score_total += obtenerScore(poblacion.get(j));
            }
            
             double[] prob =new double[tam_poblacion];
             for (int j = 0; j < tam_poblacion; j++) {
               prob[j]=obtenerScore(poblacion.get(j))/(double)score_total;
            }
            
             //crearacumalad
             int a=0;
             a++;
             System.out.println("Salida");
            
        }
        
        
        
        
        
    }

    private static void iniciarPoblacion() {
        
            for (int i = 0; i < tam_poblacion; i++) {
            List<Integer> individuo;
            individuo = new ArrayList<>();

            for (int j = 0; j < tam_genes; j++) {
                Random rnd = new Random();
                int valor = rnd.nextInt(2);
                individuo.add(valor);
            }
            poblacion.add(i,individuo);
        
        
            }
    }
    
      private static int obtenerScore(List<Integer> individuo) {
        int suma = 0;
        for (int i = 0; i < tam_genes ; i++) {
            suma += individuo.get(i);
        }
      return suma;  
    }
    
}
