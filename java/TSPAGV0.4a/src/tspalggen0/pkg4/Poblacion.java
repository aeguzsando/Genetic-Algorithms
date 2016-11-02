
package tspalggen0.pkg4;


public class Poblacion {
    // Guardamos las poblacion de rutas en un arreglo
    private Ruta[] rutas;
    
    // Constructor de poblacion
    public Poblacion(int tamPoblacion){ // Poblacion(int tamPoblacion, boolean initialize)
        rutas = new Ruta[tamPoblacion];
        // cuando se inicie la poblacion
//        if(initialize){
            // creamos n individuos
            for (int i = 0; i < getTamPoblacion(); i++) {
                Ruta nvaRuta = new Ruta();
                nvaRuta.generarRuta();
                salvarRuta(i, nvaRuta);
            }
//        }
    }
    
    // Metodo para salvar una ruta
    public void salvarRuta(int i, Ruta ruta){
        rutas[i] = ruta;
    }
    
    // Obtenemos un ruta de la poblacion
    public Ruta getRuta(int i){
        return rutas[i];
    }
    
    // Obtenemos la mejor ruta de la poblacion
    
    public Ruta getMasApto(){
        Ruta best = rutas[0];
        // recorremos el arreglo para encontrar al mejor
//        for (int i = 0; i < getTamPoblacion(); i++) {
//            if(best.getAptitud() <= getRuta(i).getAptitud()){
//                best = getRuta(i);
//            }
//        }
        for (int i = 0; i < getTamPoblacion(); i++) {
            if(best.getAptitud() >= getRuta(i).getAptitud()){
                best = getRuta(i);
            }
        }
        return best;
    }
    
    // Obtenemos el tamaño del arreglo
    public int getTamPoblacion() {
        return rutas.length;
    }
    
}
