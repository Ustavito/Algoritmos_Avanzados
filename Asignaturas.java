public class Asignaturas
{
    public static int h1 (int[][] cs){
        int a = cs[0].length; 
        int filas = cs.length; 
        int d = a + filas - 1; 
        int e = d - a; 
        
        int totalCalificaciones = 0;
        int max = 0;
        int filaMax = 0;
        int[] calificacionesPorAsig = new int[a];
        
        for (int i = 0; i < a && d > 0; i++){
            max = cs[0][i]; 
            filaMax = 0; 
            if (e > 0){
                for (int j = 1; j < filas; j++){
                    if (cs[j][i] > max){
                        max = cs[j][i];
                        filaMax = j;
                    }
                }
                e -= filaMax;
                d -= ++filaMax;
            }
            else d--;
            totalCalificaciones += max;
        }  
        return totalCalificaciones;
    }

    public static int h2(int[][] cs) {
		
		int diasExtra = cs.length -1;	
		int beneficioTotal = 0;
		
		//Sumo los beneficios de 1 dia de cada asignatura (minimo obligatorio)
		for (int i = 0; i<cs[0].length; i++) {
			beneficioTotal += cs[0][i];
		}
		
		for (int i = 1; i < cs.length; i++){ 			//Recorro a partir de la primera fila de asignaturas	
			for(int j = 0; j < cs[0].length && diasExtra > 0 ; j++ ) {
				beneficioTotal += cs[i][j];		//Voy guardando el primero de cada asignatura mientras me queden dias
				beneficioTotal -= cs[i - 1][j]; 	// Quito del beneficio total la iteracion anterior
				diasExtra-= 1;				//Resto un día del total de días extra
			}
		}
		return beneficioTotal;
	} 
}