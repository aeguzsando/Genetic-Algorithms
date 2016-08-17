#include <iostream>
#include <vector>
#include <stdlib.h>
#include <time.h>

using namespace std;

int tam_genes=15;     // Tamaño del genoma de cada individuo   VECTOR
                      //										 DE
int tam_poblacion=10; // Cantidad de individuos   			  POBLACION
int generaciones=500; // No. de generaciones establecidas para los cruzamientos.

vector<vector<int> > poblacion; // Poblacion, conjunto de individuos con su
								// genoma respectivo
								
								
// Funcion para iniciar la poblacion, generandole un genoma aleatoriamente.

void iniciarPoblacion (){
	
	for(int i = 0; i < tam_poblacion; i++)
	{
		vector<int> individuo;
		
		for(int j = 0; j < tam_genes; j++)
		{
			// Generamos un valor en el rango [0,1], lo almacenamos en num.
			int num = rand() % 2; 
			// Guardamos uno a uno (num) en el vector "individuo".
			individuo.push_back(num);
		}
     // El vector "individuo" lo almacenamos a su vez en en vector de poblacion
		poblacion.push_back(individuo);
		// generamos asi la una poblacion: poblacion [1][0,0,0,1]
		//                                           [2][0,1,1,0]
		//										     [.][  ...  ]
		//										     [n][0,0,1,1]
	}
}


// Opcional: funcion para mostrar la poblacion de individuos en cada generacion
void mostrarPoblacion()
{
	for(int i = 0; i < tam_poblacion ; i++)
	{
		for(int j = 0; j < tam_genes; j++)
		
		cout << poblacion[i][j]<< " ";
		cout << endl;   	
			
	}
}

// Funcion para obtener la puntuacion de cada individuo.

int obtenerPuntuacion (vector<int> individuo)
{    // Declaramos una variable para almacenar el puntaje del individuo.
	int suma = 0;
	for(int i = 0; i < tam_genes; i++)
	// Hasta este punto la funcion cuenta el numero de  genes del individuo
	// y los almacena.
		suma += individuo[i];
		
	return suma;
}


// Funcion de mutacion del individuo.
				//PARAMETROS
void mutacion(vector<int>& hijo) 
{
	// tomamos un punto random del tamaño de genes 
	int gen = rand() % tam_genes;
	// Mutamos el valor en el indice. si gen = 0 entonces cambia a 1, 
	// y visceversa.
	 if(hijo[gen] == 0)
	 	
	 	hijo[gen] = 1;
	 
	 else
	 	
	 	hijo[gen] = 0;
	 
	 	
}

// Funcion de cruzamiento.       //PARAMETROS
void cruzamiento (int indice_padre1, int indice_padre2, vector<int>& hijo)
{    // Tomamos un punto random entre el tamaño de genes.
	int punto1 = rand() % tam_genes;
	int punto2 = rand() % tam_genes;
	
	
	
	if(punto1 > punto2)
	{
	    for(int i = 0; i < punto1 ; i++) // cuando indice valga punto, entonces
			// tomamos los primeros valores y alamcenamos en el indice padre
			hijo.push_back(poblacion[indice_padre1][i]);
			// a partir del siguiente indice a punto, almacenamos los segundos valores,
			// de esa forma realizamos una cruza entre los valores del padre 1 y padre 2
    	for(int i = punto1 + 1; i < punto2; i++)
		    hijo.push_back(poblacion[indice_padre2][i]);
		for(int i = punto2; i < tam_genes; i++)
			
			hijo.push_back(poblacion[indice_padre1][i]);	
			
				
	}
	    
                            
}


// Funcion para obtener al mejor individuo de la poblacion
int obtenerMejor ()
{
  	// inicializamos dos variables indice mejor y score mejor
	int indice_mejor = 0;
	int score_mejor = obtenerPuntuacion(poblacion[0]);
	// Para i = 1 hasta el tamaño de la poblacion
	for(int i = 1; i < tam_poblacion; i++)
	{
		int score = obtenerPuntuacion(poblacion[i]);// almacenamos la suma que dan 
		// los 1's
		// y actualizamos si el score que obtuve es mayor que el mejor score
		if(score > score_mejor)
		{
			indice_mejor = i; // el indice del individuo con mejor score
			score_mejor = score; // y su score
			
			// p. ej individuo[3][0,1,1,1,1]
			// indice_mejor = 3 
            // score_mejor = 4
		}
	}
	
	return indice_mejor;
}


int main(int argc, char* argv[])
{
	
	iniciarPoblacion();
	
	
	for(int i = 0; i < generaciones; i++)
	{
		   	   // Escojemos dos padres
				  int indice_padre1 = rand() % tam_poblacion; 
				  int indice_padre2;
			   // Evitamos que los dos padres no sean iguales  
				  do
				  {
					  indice_padre2 = rand() % tam_poblacion;
				  } 
				  while(indice_padre1 == indice_padre2);
				  
				  vector<int> hijo;
			  	  // Aplicamos el cruzamiento de 1 punto, generamos un hijo
			  	  cruzamiento(indice_padre1, indice_padre2, hijo);
			  	  // Mutamos el hijo
			  	  mutacion(hijo);
						
				  int score_padre = obtenerPuntuacion(poblacion[indice_padre1]);
				  int score_hijo =  obtenerPuntuacion(hijo);	
				// si la puntuación (score) del hijo es mejor
                // qie la del padre, entonces la del hijo la sustituye
					if(score_hijo > score_padre)
					{
						// hacemos una copia de sus genes en el padre 1
						for(int k = 0; k < tam_genes; k++)
						
							poblacion[indice_padre1][k] = hijo[k];
						
					}	
			  
			   
			   
			   cout << "Generacion " << i + 1 << endl;
			   cout << "Mejor " ;
			    
			   int indice_mejor = obtenerMejor();
			   int score_mejor = obtenerPuntuacion(poblacion[indice_mejor]);
			   
			   
			   for(int j = 0; j < tam_genes; j++)
			  
			  	 
				 cout << poblacion[indice_mejor][j] << " " ;
	  	         cout << "\nPuntuacion: " << score_mejor << "\n\n";
	  	        // mostrarPoblacion();
	  	        // Si se encontro una solucion optima global, el ciclo for termina
				// aunque no hayan pasado todas las generaciones asignadas 
	  	         if(score_mejor == tam_genes)
	  	         	break;
	  	         	
		   }	
		   
		   return 0;
	}
	 

















