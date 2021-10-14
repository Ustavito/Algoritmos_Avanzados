public class Metodo_h2 {
	
	public static int h2(int[][] cs) {
		
		int diasExtra = cs.length -1;	
		int beneficioTotal = 0;
		
		//Sumo los beneficios de 1 dia de cada asignatura (minimo obligatorio)
		for (int i = 0; i<cs[0].length; i++) {
			beneficioTotal += cs[0][i];
		}
		
		for (int i = 1; i < cs.length; i++){ //Recorro a partir de la primera fila de asignaturas
			
			for(int j = 0; j < cs[0].length && diasExtra > 0 ; j++ ) {
				beneficioTotal += cs[i][j];	//Voy guardando el primero de cada asignatura mientras me queden dias
				diasExtra-= 1;				//Resto un día del total de días extra
			}
		}
		
		return beneficioTotal;
	}
}
