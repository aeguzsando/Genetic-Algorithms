/*  GA TSP kroA100 test, using Swap mutation y 2 point crossover, testing x35. By: Eric Guzman
	Special thanks to Lee Jacobson */


package tspalggen0.pkg4;


public class TSPalggen04 {


    public static void main(String[] args) {
        
        String version = "kroA100.tsp";// Be free to change this version to kroaB100.tsp
        lector.leer(version);
        System.out.println("TSP AG Using: "+version);
        
        int pruebas = 35;
        System.out.println("i|__D0__|__D1__");
        for (int i = 0; i < pruebas; i++) {
            System.out.print((i+1)+" ");    
            Poblacion pob = new Poblacion(50);//Poblacion(10, true)
//            System.out.println("Ruta inicial: "+pob.getMasApto());
//            System.out.println("Distancia inicial: "+pob.getMasApto().
//                    getDistanciaRuta());
            System.out.print(pob.getMasApto().getDistanciaRuta()+" ");
            int aux1 = pob.getMasApto().getDistanciaRuta();
            int aux2 = 0;
            int Generaciones = 100000;
        
            for (int j = 0; j < Generaciones; j++) {
                pob = Algoritmo4a.evolucionarPoblacion(pob);
                //System.out.println((j+1)+": "+pob.getMasApto().getDistanciaRuta());
    //            System.out.println(pob.getMasApto().getDistanciaRuta());
                aux2 = pob.getMasApto().getDistanciaRuta();
            }
            int dif = aux1 - aux2;
            System.out.print(pob.getMasApto().getDistanciaRuta());
//            System.out.println("Ruta optima: "+pob.getMasApto());
//            System.out.println("Distancia final: "+pob.getMasApto().
//                    getDistanciaRuta());
//            System.out.println("Diferencia distancia: "+dif);
//            System.out.println("************************************************"
//                    + "*******************************************************"
//                    + "*************************************");
            System.out.println(" ");
        }
    }
    
}
