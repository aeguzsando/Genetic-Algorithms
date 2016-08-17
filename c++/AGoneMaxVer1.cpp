#include <iostream>
#include <vector>
#include <stdlib.h>
#include <time.h>

/*
   
*/
using namespace std;

int tam_genes = 300;   // Tamaño del genoma de cada individuo   VECTOR
//										 DE
int tam_poblacion = 1000; // Cantidad de individuos   			  POBLACION
int generaciones = 10000; // No. de generaciones establecidas para los cruzamientos.

vector<vector<int> > poblacion; // Poblacion, conjunto de individuos con su
// genoma respectivo

///////////////

///////////////
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

			cout << poblacion[i][j] << " ";
		cout << endl;

	}
}

// Funcion para obtener la puntuacion de cada individuo.

int obtenerPuntuacion (vector<int> individuo)
{
	// Declaramos una variable para almacenar el puntaje del individuo.
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
{
	// Tomamos un punto random entre el tamaño de genes.
	int punto = rand() % tam_genes;

	for(int i = 0; i < punto ; i++) // cuando indice valga punto, entonces
		// tomamos los primeros valores y alamcenamos en el indice padre
		hijo.push_back(poblacion[indice_padre1][i]);
	// a partir del siguiente indice a punto, almacenamos los segundos valores,
	// de esa forma realizamos una cruza entre los valores del padre 1 y padre 2
	for(int i = punto + 1; i < tam_genes; i++)
		hijo.push_back(poblacion[indice_padre2][i]);

	// p. ej.   padre1 = [0,1,1,0,0(punto),1,0]   padre2 = [0,1,0,1,1(punto),1,1]
	//            indice_padre1 = [0,1,1,0,0]          indice_padre2 = [1,1]
	//            							   X
	//                               hijo = [0,1,1,0,0,1,1]
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
	srand(time(NULL));

	for(int i = 0; i < generaciones; i++)
	{



		int indice_padre1 = 0;
		int indice_padre2 = 0;
		int scoreTotal = 0;
		//double prob1 = 0; // probabilidad de p para ser seleccionado.
		//double prob2 = 0;
		//double rprob = 0; // Probabilidad random (se compara con prob1).
		//double rnum = 0;
		//double prob_acc = 0; // Probabilidad acumulada
		// Escojemos dos indices padre con el metodo ruleta
		// Calculamos el score total de la poblacion, sumando los scores de
		// todos.
		//double sum_prob_acc = 0;
		//double prob_acc_i = 0;

		for(int h = 0; h < tam_poblacion ; h++)
		{
			scoreTotal += obtenerPuntuacion(poblacion[h]);
		}
		////////////////////////////////////////////////////////////////
		//tenemos scoreotal
		float probabilidades[tam_poblacion];
		for(int h = 0; h < tam_poblacion ; h++)
		{
			probabilidades[h] = obtenerPuntuacion(poblacion[h])/scoreTotal;
		}
		// construimoslaaumulada
		
		float acumulada[tam_poblacion];
		acumulada[0] = probabilidades[0];
		for(int h = 1; h < tam_poblacion ; h++)
		{
			acumulada[h] = acumulada[h-1]+probabilidades[h];
		}
		acumulada[tam_poblacion-1] = 1.0;
		
		//determinar indices
		//indice 1//
		float aleatorio=((float) rand() / RAND_MAX);
		int index =0;
		while(aleatorio > acumulada[index])
		{
			index++;
		}
		indice_padre1 = index;
		//indice 2//
		aleatorio=((float) rand() / RAND_MAX);
		index =0;
		while(aleatorio > acumulada[index])
		{
			index++;
		}
		indice_padre2 = index;
		/////////////////////////////////////////////////////////////////
		
	
		
		
	/*
		int index = 1;	
		
		
		do{
			indice_padre1 = rand() % tam_poblacion;
			while(index <= indice_padre1)
			{
			   index++;
			   prob_acc = obtenerPuntuacion(poblacion[index]) /scoreTotal + obtenerPuntuacion(poblacion[index-1]) /scoreTotal;
			   sum_prob_acc += prob_acc;
			   prob_acc_i = (obtenerPuntuacion(poblacion[indice_padre1]) / scoreTotal) + prob_acc;
				   			   				   
			}
	   }while(rprob < prob_acc_i);
	   
	   
   		do{
			indice_padre2 = rand() % tam_poblacion;
			while(index <= indice_padre2)
			{
			   index++; 		  		   	
			   prob_acc = obtenerPuntuacion(poblacion[index+1]) /scoreTotal + obtenerPuntuacion(poblacion[index-1]) /scoreTotal;
			   sum_prob_acc += prob_acc;
			   prob_acc_i = (obtenerPuntuacion(poblacion[indice_padre2]) / scoreTotal) + prob_acc;
				   
			}
	   }while(rprob < prob_acc_i);

		
	*/	
	/*
		int r_index1 = rand() % tam_poblacion;
		
		//	int r_index1;
		for(int h = 1; h < r_index1; h++)
		{
			   
			   prob_acc = obtenerPuntuacion(poblacion[h]) /scoreTotal + obtenerPuntuacion(poblacion[h-1]) /scoreTotal;
			   sum_prob_acc += prob_acc;
			   double prob_acc_p1 = (obtenerPuntuacion(poblacion[r_index1]) / scoreTotal) + prob_acc;
			   
			   if(prob_acc_p1 > rprob)
			   {
				   r_index1 = indice_padre1;
				   break;
			   }
		}
			   
		
		
		int r_index2 = rand() % tam_poblacion;	
		
		
		//	int r_index1;
		for(int h = 1; h < r_index2; h++)
		{
			   
			   prob_acc = obtenerPuntuacion(poblacion[h]) /scoreTotal + obtenerPuntuacion(poblacion[h-1]) /scoreTotal;
			   sum_prob_acc += prob_acc;
			   double prob_acc_p2 = (obtenerPuntuacion(poblacion[r_index2]) / scoreTotal) + prob_acc;
			   
			   if(prob_acc_p2 > rprob)
			   {
				   r_index2 = indice_padre1;
				   break;
			   }	   
	
		}
		
*/
		
		
		
		

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
		// cout << "Probabilidad " << prob << endl;
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


















