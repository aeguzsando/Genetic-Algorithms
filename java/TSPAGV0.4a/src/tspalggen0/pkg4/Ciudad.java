
package tspalggen0.pkg4;

import java.util.ArrayList;


public class Ciudad {
    private int x;
    private int y;
    // Utilizamos una lista para guardar las ciudades creadas
    private static ArrayList ciudades = new ArrayList<Ciudad>();
    // Constructor de la ciudad (aleatoriamente)
    public Ciudad(){
        this.x = (int) (Math.random()*1000);
        this.y = (int) (Math.random()*1000);         
    }
    
    // Constructor de una ciudad (manual coordenadas(x,y) )
    public Ciudad(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Getter de coordenada x
    public int getX(){
        return this.x;
    }
    
    // Getter de coordenada y
    public int getY(){
        return this.y;
    }
    
    // Obetner la distancia desde el inicio a un punto dado
    public double distanciaPaP(Ciudad ciudad){
        int distanciaX = getX() - ciudad.getX();
        int distanciaY = getY() - ciudad.getY();
        double distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
        return distancia;
    }
    
    // Agregamos una ciudad 
    public static void addCiudad(Ciudad ciudad){
        ciudades.add(ciudad);
    }
    
    // Obtenemos un ciudad 
    public static Ciudad getCiudad(int i){
        return (Ciudad) ciudades.get(i);
    }
    
    // Obtenemos el numero de ciudades
    public static int getNumCiudades(){
        return ciudades.size();
    }
    
    @Override
    public String toString(){
        return "("+getX()+","+getY()+")";
    }
}
