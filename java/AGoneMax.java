// Version alfa de AG one max en java. Basado en el Prototipo AG one max Ver.
// alfa codificado con C++.
package agonemax;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AGoneMax {
    static int tam_genes = 21;   							
    static int tam_poblacion = 100; 
    static int generaciones = 2000; 
    static List<List<Integer> > poblacion = new ArrayList<>();
    
/*                      VARIABLES DE PRUEBA (AQUI)                            */
//============================================================================//
    //static int arr[][] = new int[tam_poblacion][tam_genes]; 
    
    
    
    
//============================================================================//
    
    public static void main(String[] args) {
        
     iniciarPoblacion();
     
        for (int i = 0; i < generaciones; i++) {
            
            Random  rnd = new Random();
            int indice_padre1 = rnd.nextInt(poblacion.size());
            int indice_padre2;
                
                do {                
                    indice_padre2 = rnd.nextInt(poblacion.size());
                } while (indice_padre1 == indice_padre2);
                
            List<Integer> hijo = new ArrayList<>();
            
            
            cruzamiento(indice_padre1, indice_padre2, hijo);
            
            mutacion(hijo);
            
            int score_padre = obtenerScore(poblacion.get(indice_padre1));
            int score_hijo = obtenerScore(hijo);
//============================================================================//  
            if (score_hijo > score_padre) {
                for (int j = 0; j < tam_genes ; j++) {
                    poblacion.get(indice_padre1).set(j, hijo.get(j));
                }
            }
//============================================================================//

            System.out.println("Generacion " + (i+1));
            System.out.println("Mejor ");  
           
//============================================================================//      
            int indice_mejor = obtenerMejor();
            int score_mejor = obtenerScore(poblacion.get(indice_mejor));
        
//            List<Integer> mejor = poblacion.get(indice_mejor);
//            for(int j= 0; j<mejor.size();j++)
//                System.out.print(" "+ mejor.get(j)+" ");
            
            List<Integer> mejor = poblacion.get(indice_mejor);
            
            
//            for (int j = 0; j < tam_genes; j++)

            for(int j = 0; j < mejor.size(); j++)
                System.out.print(" "+mejor.get(j)+" ");
            
            System.out.println(" ");        

            System.out.println("Puntación " + score_mejor);  
            
        
            if(score_mejor ==  tam_genes){
                break;
            }
            
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
    
        private static void mostrarPoblacion() {
        for(int i = 0; i < tam_poblacion ; i++)
	{
		for(int j = 0; j < tam_genes; j++){
                   System.out.print(j);
                }
	
	}
        throw new UnsupportedOperationException("Not supported yet."); 
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

    private static int obtenerScore(List<Integer> individuo) {
        int suma = 0;
        for (int i = 0; i < tam_genes ; i++) {
            suma += individuo.get(i);
        }
      return suma;  
    }

    private static int obtenerMejor() {
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


