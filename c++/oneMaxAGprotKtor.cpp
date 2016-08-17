#include <iostream>
#include <vector>
#include <stdlib.h>
#include <time.h>


using namespace std;

int n_genes = 20; //no. de genes de cada individuo (genoma).       VECTOR DE LA 
int n_individuos = 20; // no. de individuos en la poblacion.        POBLACION
int k_torneo = 3; // Tamaño del torneo
                      
int generaciones = 3000; //no. de generaciones determinadas para la evolucion.


vector<vector<int> > poblacion; // vector de poblacion:guardamos en este 
							    // vector a cada individuo con 
                                // sus genes respectivos.  
                             
                             
// Funcion para iniciar la poblacion

void iniciarPoblacion()
{
	 for(int i = 0; i < n_individuos; i++)
	 {
		 vector<int> individuo;
		 for(int j = 0; j < n_genes; j++)
		 {
		 	 int num = rand() % 2;  // Tomamos un valor random de [0,1]
			 individuo.push_back(num); // Almacenamos ese valor random.
		 }
		 
		 poblacion.push_back(individuo);
	 }	
	
}

// Funcion para mostrar la poblacion de cada generacion.

void mostrarPoblacion()
{
	   for(int i = 0; i < n_individuos; i++)
	   {
		   for(int j = 0; j < n_genes; j++)
		   cout << poblacion[i][j] << " ";
	   	   cout << endl;	   
		   
	   }		
}


// Funcion para medir la puntuacion del individuo.

int puntuacionIndividuo(vector<int> individuo)
{
	int suma = 0;
	for(int i = 0; i < n_genes; i++)
			
           suma += individuo[i];
		
	return suma;	
}


// Funcion para mutar un gen de un individuo.
void mutacion(vector<int>& individuo)
{
	// [0, n_genes -1]
	int gen = rand() % n_genes;
	if (individuo[gen] == 0)
		individuo[gen] = 1;
	else
	    individuo[gen] = 0;
}

// funcion para encontrar un punto de cruza entre indices padre formando
// un indice hijo.

void cruzamiento(int indice_p1, int indice_p2, vector<int>& hijo)
{
	int punto = rand() % n_genes;
	
	for(int i = 0; i <= punto ; i++)
		hijo.push_back(poblacion[indice_p1][i]);
	for(int i = punto + 1; i < n_genes; i++)
		hijo.push_back(poblacion[indice_p2][i]);
			
}

int obtenerMejor()
{
	int indice_mejor = 0;
	int score_mejor = puntuacionIndividuo(poblacion[0]);
	 for(int i = 1; i < n_individuos; i++)
	 {
		 int score = puntuacionIndividuo(poblacion[i]);
		 if(score > score_mejor)
		 {
			 indice_mejor = i;
			 score_mejor = score;
		 }
	 }
	return indice_mejor;
}


int main(int argc, char *argv[])
{
	
	iniciarPoblacion();
	
	for(int i = 0; i < generaciones; i++)
	{   
	
		// Declaramos dos indices padre
		int indice_p1 = rand() % n_individuos;
        int indice_p2;
           
        // Iniciamos el K torneo
		for(int k = 0; k < k_torneo; k++)
		{
		/* Declaramos dos competidores, que competiran con los indices padres
		pudiendo tomar su lugar */	
		   
        int comp_1;     
		int comp_2;
		// Declaramos variables para medir el score de competidores
		int score_p1;
		int score_c1;
		int score_p2;
		int score_c2;    
		// Evitamos redundancia en todos los competidores (que no sean el mismo indice)	
			   do
				{
					 
					 indice_p2 = rand() % n_individuos;	
					 comp_1 = rand() % n_individuos;
					 
				} while(indice_p1 == comp_1 && indice_p1 == indice_p2);
		// Calculos el score		
				score_p1 = puntuacionIndividuo(poblacion[indice_p1]);
				score_c1 = puntuacionIndividuo(poblacion[comp_1]);
		// Actualizamos el valor de los indices padre, dependiendo que indice sea ganador		
				if(score_c1 > score_p1 && score_c2 > score_p2)
				{
					comp_1 = indice_p1;
					comp_2 = indice_p2;
				}
		/* Se repite el proceso k veces hasta tener dos indice p ganadores 
		del torneo para continuar */	
		}
		
		
			
			
				
				vector<int> hijo;
				
				// Aplicamos la funcion de cruza
				
				cruzamiento(indice_p1, indice_p2, hijo);
				
				// Aplicamos la funcion de mutacion
				mutacion(hijo);
					
				int score_p = puntuacionIndividuo(poblacion[indice_p1]);
				int score_h = puntuacionIndividuo(hijo);
				// Si la puntuacion del p1 fue mayor que la del hijo,
				// sustituimos el p1 por el hijo
				if(score_h > score_p)
				{
					// Haciendo una copia de lo genes del hijo en el indice de p1
					for(int k = 0; k < n_genes; k++)
					
					    poblacion[indice_p1][k] = hijo[k];	 	
					
				}	
				
		
		// Impresion de resultados
		cout <<"Generacion: " << i + 1 << endl;
		cout <<"Mejor: ";
		
		
		int indice_mejor = obtenerMejor();
		int score_mejor = puntuacionIndividuo(poblacion[indice_mejor]);
	
		for(int j = 0; j < n_genes; j++)
		
        
		cout << poblacion[indice_mejor][j] << " ";
		cout <<"\nPuntuacion: "<< score_mejor <<"\n\n";	
		
		if(score_mejor == n_genes)
			
		break;
	}
	
	return 0;
}
















