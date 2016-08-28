
package agonemax;

import static agonemax.AGoneMax.tam_genes;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;



public class AGoneMaxV03 {
    
    static int tam_genes = 100;   							
    static int tam_poblacion =500; 
    static int generaciones = 100000; 
    static List<List<Integer> > poblacion = new ArrayList<>();
    
    public static void main(String[] args){
        
        iniciarPoblacion();
        
        for (int i = 0; i < generaciones; i++) {
            int indice_padre1;
            int indice_padre2;
            int score_total = 0;
            
            for (int j = 0; j < tam_poblacion; j++) {
                score_total += obtenerScore(poblacion.get(j));
            }
            
             double[] prob = new double[tam_poblacion];
             for (int j = 0; j < tam_poblacion; j++) {
               prob[j]=obtenerScore(poblacion.get(j))/(double)score_total;
            }
            
             
             //crear acumalada
             
             double[] acumulada = new double[poblacion.size()];
             acumulada[0] = prob[0];
             
             for (int j = 1; j <poblacion.size(); j++) {
                acumulada[j] = acumulada[j-1] + prob[j];
            }
            
             acumulada[poblacion.size() - 1] = 1.0;
             
             // Determinar indices
                // Indice padre 1
             Random rnd = new Random();
             double aleatorio;
             // Generamos un numero aleatorio entre 0.0 y 1.0
             aleatorio = rnd.nextDouble();
             int index = 0;
             
             while (aleatorio > acumulada[index]) {                
                index++;
            }
             
             indice_padre1 = index;
             
                // Indice padre 2
             aleatorio = rnd.nextDouble(); 
             index = 0;
             
             while(aleatorio > acumulada[index]){
                 index++;
             }
             
             indice_padre2 = index;
             
             
             // Creamos al hijo
             
             List<Integer> hijo =  new ArrayList<>();
             
             //Aplicamos el cruzamiento
             
             cruzamiento(indice_padre1, indice_padre2, hijo);
             
             // Mutacion de hijo
             
             mutacion(hijo);
             
             // Obtenemos al mejor de cada generacion
             
             int score_padre = obtenerScore(poblacion.get(indice_padre1));
             int score_hijo = obtenerScore(hijo);
             
             //Copiamos en indice padre 1 en hijo si el score de este es mayor al del padre 
             
             if(score_hijo > score_padre){
                 
                 for (int j = 0; j < tam_genes; j++) {
                      poblacion.get(indice_padre1).set(j, hijo.get(j));
                 }
             }
             
             System.out.println("Generacion"+(i+1));
             System.out.println("Mejor");
             
            int indice_mejor = obtener_mejor();
            int score_mejor =obtenerScore(poblacion.get(indice_mejor));
            List<Integer> mejor = poblacion.get(indice_mejor);
            
            for (int j = 0; j < tam_genes; j++) 
                System.out.print(" "+mejor.get(j)+" ");
            
            
            System.out.println(" ");    
            System.out.println("Puntuacion: "+score_mejor);
            
            if(score_mejor == tam_genes)
                
                break;    
             
//             int a=0;
//             a++;
//             System.out.println("Salida");
            
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

    private static void cruzamiento(int indice_padre1, int indice_padre2, List<Integer> hijo) {
        
        Random rnd = new Random();
        int punto1 = rnd.nextInt(tam_genes);
	int punto2 = rnd.nextInt(tam_genes);
                
        if(punto1 < punto2){
            for (int i = 0; i < punto1; i++){ 
                Integer temp =poblacion.get(indice_padre1).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
            for (int i = punto1; i < punto2; i++) {
                Integer temp =poblacion.get(indice_padre2).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
            for (int i = punto2; i < tam_genes; i++) {
                Integer temp =poblacion.get(indice_padre1).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
        }else{
            
            for (int i = 0; i < punto2; i++){ 
                Integer temp =poblacion.get(indice_padre1).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
            for (int i = punto2; i < punto1; i++) {
                Integer temp =poblacion.get(indice_padre2).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
            for (int i = punto1; i < tam_genes; i++) {
                Integer temp =poblacion.get(indice_padre1).get(i);
                hijo.add(new Integer(temp.intValue()));
            }
        }
        
    }

    private static void mutacion(List<Integer> hijo) {
        Random rnd = new Random();
        int gen;
        gen = rnd.nextInt(tam_genes);
        if (hijo.get(gen) == 0) {
            hijo.set(gen, 1);
        }else{
            hijo.set(gen, 0);
        }
    }

    private static int obtener_mejor() {
        int indice_mejor = 0;
        int score_mejor = obtenerScore(poblacion.get(0));
        for (int i = 1; i < tam_poblacion; i++) {
            int score = obtenerScore(poblacion.get(i));
            if (score > score_mejor) {
                indice_mejor = i;
                score_mejor = score;
            }
        }
        return indice_mejor;
    }
    
}
