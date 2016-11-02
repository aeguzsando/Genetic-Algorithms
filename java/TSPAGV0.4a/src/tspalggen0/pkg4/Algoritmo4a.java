/*  Be free to change the parameters or the genetic operators. 
Some of the operators that you will find in this code are:
	- Roulette wheel Selection
	- Tournament Selection
	- simple crossover(1 cut point)
	- 2 point crossover
	- swap mutation (swapping two cities)
	- nswap mutation (probabbility  of swapping two the cities more than once)
	*/
package tspalggen0.pkg4;


public class Algoritmo4a {
    private static final double mutationRate = 0.015; // Best used on the mutation method, it's no need to mute all the new pops, 
        // if this happens the following pop'll always keep the fluctuation on his genetic material. There will be no optmization.
        // So, always keep a low rate, but high enough on your algorithm
    private static final int kTorneo = 3;
    private static boolean elitism = true; // Best used on the algorithm method itself, it is better to maintain some genetic material
                                           // as needed
    
    // Evolucionamos la poblacion en una generacion
    public static Poblacion evolucionarPoblacion(Poblacion poblacion){
        
        Poblacion nvaPoblacion = new Poblacion(poblacion.getTamPoblacion()); 
        
        int elite = 0;
        if(elitism){ // When elitism is activated
            nvaPoblacion.salvarRuta(0, poblacion.getMasApto()); // Keep the best "Individual" (i mean tour or "ruta") on the top of the list
            elite = 1; // So, the index changes to 1
        }
        // Cruce de la poblacion - start the loop on the index = elite 
        for (int i = elite; i < nvaPoblacion.getTamPoblacion(); i++) {
            // Seleccionamos los padres
            Ruta padre1 = seleccionRuleta(poblacion);
            Ruta padre2 = seleccionRuleta(poblacion);
            // Cruzamos los padres
            Ruta hijo = cruzamiento2cut(padre1, padre2);
            // Salvamos al hijo en la poblacion nueva
            nvaPoblacion.salvarRuta(i, hijo);
        }
        // Mutamos la nueva poblacion para incrementar la variedad
        for (int i = elite; i < nvaPoblacion.getTamPoblacion(); i++) {
            mutarSwap(nvaPoblacion.getRuta(i));
        }
        
        return nvaPoblacion;
    }
    
    // Realizamos el cruce de ambos padres
    public static Ruta cruzamiento2cut(Ruta padre1, Ruta padre2){
        // creamos un ruta vacia para el hijo
        Ruta hijo = new Ruta();
        // establecemos puntos de corte para el padre uno
        int corte1 = (int) Math.random() * padre1.tamRuta();
        int corte2 = (int) Math.random() * padre1.tamRuta();
        
        // Agregamos las subruta del padre uno a la ruta hijo
        for (int i = 0; i < hijo.tamRuta(); i++) {
            // Si el corte 1 es menor al corte 2
            if(corte1 < corte2 && i > corte1 && i < corte2){
                hijo.setCiudad(i, padre1.getCiudad(i));
            }// Si el corte 1 es mayor
            else if(corte1 > corte2){
                // y si el indice respectivo 
                if(!(i < corte1 && i > corte2)){
                    hijo.setCiudad(i, padre1.getCiudad(i));
                }
            }
        }
        
        // Agregamos la subruta del padre dos al hijo
        for (int i = 0; i < padre2.tamRuta(); i++) {
            // si el hijo aun no tiene ciudades
            if(!hijo.containsCiudad(padre2.getCiudad(i))){
                // Buscamos el espacio libre en la ruta del hijo
                for (int j = 0; j < hijo.tamRuta(); j++) {
                    // al encontrar el espacio lo agrega
                    if(hijo.getCiudad(j) == null){
                        hijo.setCiudad(j, padre2.getCiudad(i));
                        break;
                    }
                }
            }
        }
        return hijo;
    }
    
    public static Ruta cruzamiento1cut(Ruta padre1, Ruta padre2){
         Ruta hijo = new Ruta();
        int corte = (int) Math.random() * padre1.tamRuta();

        for (int i = 0; i < corte; i++) {
            hijo.setCiudad(i, padre1.getCiudad(i));
        }
        
        for (int i = corte; i < padre1.tamRuta(); i++) {
            hijo.setCiudad(i, padre2.getCiudad(i));
        }
        return hijo;
    }
    
    private static void mutarNswap(Ruta ruta){
        for (int posRuta1 = 0; posRuta1 < ruta.tamRuta(); posRuta1++) {
            // Comparamos una tasa aleatoria con la tasa de mutacion
            if (Math.random() < mutationRate){
                // obtenemos una segunda posicion (posRuta2) en la ruta
                int posRuta2 = (int) (ruta.tamRuta() * Math.random());
                // Obtenemos las ciudades que seran intercambiadas
                Ciudad ciudad1 = ruta.getCiudad(posRuta1);
                Ciudad ciudad2 = ruta.getCiudad(posRuta2);
                
                // realizamos el swapping
                ruta.setCiudad(posRuta2, ciudad1);
                ruta.setCiudad(posRuta1, ciudad2);
            }
        }
    }


    // Mutar la poblacion nueva (swapping)
    private static void mutarSwap(Ruta ruta){
        
        int posRuta1 = (int) (ruta.tamRuta()* Math.random());
             
             
             if (Math.random() < mutationRate) {
                int posRuta2 = (int) (ruta.tamRuta()* Math.random());
                Ciudad ciudad1 = ruta.getCiudad(posRuta1);
                Ciudad ciudad2 = ruta.getCiudad(posRuta2);
                
                ruta.setCiudad(posRuta2, ciudad1);
                ruta.setCiudad(posRuta1, ciudad2);

            }


    }
    
    // Seleccionamos candidatos para el cruce
    private static Ruta seleccionKtorneo(Poblacion poblacion){
        // Creamos una poblacion de candidatos en el torneo
        Poblacion torneo = new Poblacion(kTorneo);//Poblacion(kTorneo, false)
        // por cada lugar en el torneo obtenemos un candidato aleatorio
        for (int i = 0; i < kTorneo; i++) {
            int rndPadre = (int) (Math.random() * poblacion.getTamPoblacion());
            torneo.salvarRuta(i, poblacion.getRuta(rndPadre));
        }
        // Obtenemos la mejor ruta
        Ruta best = torneo.getMasApto();
        return best;
                
    }
    
    
   private static Ruta seleccionRuleta(Poblacion ruleta){
       int score_total = 0;
        for (int i = 0; i < ruleta.getTamPoblacion(); i++) {
            score_total += ruleta.getRuta(i).getAptitud();
        }
        
        float[] probabilidades = new float[ruleta.getTamPoblacion()];
        for (int i = 0; i < ruleta.getTamPoblacion(); i++) {
            probabilidades[i] = (float) (ruleta.getRuta(i).getAptitud()/score_total);
        }
        
        float[] acumuladas = new float[ruleta.getTamPoblacion()];
        acumuladas[0] = probabilidades[0];
        for (int i = 1; i < ruleta.getTamPoblacion(); i++) {
            acumuladas[i] = acumuladas[i-1] + probabilidades[i];
        }
        acumuladas[ruleta.getTamPoblacion()-1] = (float) 1.0;
        float rnd = (float) Math.random();
        int index = 0;
        while (rnd > acumuladas[index]) {            
            index++;
        }
        Ruta best = ruleta.getRuta(index);
        return best;

   }
    
}
