public class P2
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
            calificacionesPorAsig[i] = max;
        }
        imprimir(calificacionesPorAsig); 
        return totalCalificaciones;
    } 

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
            System.out.print(calificaciones [i] + " ");
        }
    }
}