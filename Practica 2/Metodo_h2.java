public class Metodo_h2 {
	
	public static int h2(int[][] cs) {
		
		int beneficios[] = new int[cs[0].length];
        int diasExtra = cs.length -1;    
        int beneficioTotal = 0;
        
        //Sumo los beneficios de 1 dia de cada asignatura (minimo obligatorio)
        for (int i = 0; i<cs[0].length; i++) {
            beneficioTotal += cs[0][i];
            beneficios[i] = cs[0][i];
        }
        
        for (int i = 1; i < cs.length; i++){ 
            
            for(int j = 0; j < cs[0].length && diasExtra > 0 ; j++ ) {
                beneficioTotal += cs[i][j]; 
                beneficioTotal -= cs[i-1][j];
                beneficios[j] = cs[i][j];
                diasExtra-= 1;                
            }
        }
        
        
        imprimir(beneficios);
        return beneficioTotal;
    }
    
    public static void imprimir(int[] calificaciones){
        for (int i = 0; i < calificaciones.length; i++){
            System.out.println(calificaciones [i] + " ");
        }
    }
}
