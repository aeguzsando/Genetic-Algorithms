
package tspalggen0.pkg4;

import java.util.ArrayList;
import java.util.Collections;


public class Ruta {
    // Guardamos un conjunto de ciudades (ruta de ciudades)
    private ArrayList ruta  = new ArrayList<Ciudad>();
    // Inicilizamos en 0 distancia y aptitud
    private int distancia = 0;
    private double aptitud = 0;
    
    // Construimos una ruta vacia
    public Ruta(){
        for (int i = 0; i < Ciudad.getNumCiudades(); i++) {
            ruta.add(null);
        }
    }
    
    // Constructor de la ruta
    public Ruta(ArrayList ruta){
        this.ruta = ruta;
    }
    
    // Creamos un "individuo" aleatoriamente
    public void generarRuta(){
        // Recorremos la lista de ciudades y las agregamos a la ruta
        for (int indiceCiudad = 0; indiceCiudad < Ciudad.getNumCiudades(); indiceCiudad++) {
            setCiudad(indiceCiudad, Ciudad.getCiudad(indiceCiudad));
        }
        // Reornedamos las ciudades en la ruta generada
        Collections.shuffle(ruta);
    }
    
    // Obtenemos una ciudad en una posicion especifica
    public Ciudad getCiudad(int posicionRuta){
        return (Ciudad) ruta.get(posicionRuta);
    }
    // colocamos una ciudad en la ruta en una posicion especifica
    public void setCiudad(int posicionRuta, Ciudad ciudad) {
       ruta.set(posicionRuta, ciudad);
       // Si las rutas son alteradas reiniciamos la aptitud y la distancia
       aptitud = 0;
       distancia = 0;
    }
    
    // Obtenemos la aptitud
    public double getAptitud(){
        if (aptitud == 0){
            aptitud = getDistanciaRuta();
//            aptitud = 1/(double) getDistanciaRuta();
        }
        return aptitud;
    }
            
            
    
    // Obtenemos la distancia total de la ruta
    public int getDistanciaRuta(){
        if (distancia == 0){
            int distanciaRuta = 0;
            // Recorremos la cudades 
            for (int indiceCiudad = 0; indiceCiudad < tamRuta(); indiceCiudad++) {
                // Obtenemos la ciudad desde donde viajamos
                Ciudad origen = getCiudad(indiceCiudad);
                // Obtenemos la ciudad a donde viajamos
                Ciudad destino;
                // Si estamos en la ultima ciudad de la ruta
                if(indiceCiudad+1 < tamRuta()){
                    destino = getCiudad(indiceCiudad+1);
                }else{
                    destino = getCiudad(0);
                }
                // obtenemos la distancia entre dos ciudades y las sumamos
                distanciaRuta += origen.distanciaPaP(destino);
            }
            distancia = distanciaRuta;
        }
        return distancia;
    }
    
    // Obtenemos la aptitud de la ruta    
    public int tamRuta() {
        return ruta.size();
    }

    
    // Verificamos que la ruta contenga una ciudad
    public boolean containsCiudad(Ciudad ciudad){
        return ruta.contains(ciudad);
    }
    
    @Override
    public String toString(){
        String c = "[";
        for (int i = 0; i < tamRuta(); i++) {
            
                c += getCiudad(i)+", ";
            if(i == tamRuta()-1){
                c+= getCiudad(0)+ "]";
            }
        }
        return c;
    }
}
