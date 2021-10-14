
/**
 * Write a description of class P2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Metodo_h1
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
}
