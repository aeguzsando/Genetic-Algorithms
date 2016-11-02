/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspalggen0.pkg4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric
 */
public class lector {
    
    public static void leer(String nombre){
        
        try
        {
        FileReader f = new FileReader(nombre);
        BufferedReader b = new BufferedReader(f);
        int NumeroCiudades = Integer.valueOf(b.readLine());
        for(int i=0; i< NumeroCiudades; i++)
        {
            String cadena = b.readLine();
            StringTokenizer stokenizer = new StringTokenizer(cadena," ");
            stokenizer.nextToken();
            int X = Integer.valueOf(stokenizer.nextToken());
            int Y = Integer.valueOf(stokenizer.nextToken());
            Ciudad c= new Ciudad(X, Y);
            Ciudad.addCiudad(c);
        }
        
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
