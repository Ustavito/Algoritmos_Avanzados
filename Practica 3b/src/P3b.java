public class P3b {
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
                        if (cs[j][i] > max && j <= e){
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

        public static int calificacionesBacktracking(int[][] cs){

            //declaración de variables

            int a = cs[0].length;
            int f = cs.length;
            int d = a + f - 1;
            int e = d - a;

            int[] solParcial = new int[a];
            int[] solOptima = new int[a];

            int cOpt = buscarOptimo(cs, 0, e, a, d, solParcial, 0, solOptima, -1);
            imprimir(solOptima);

            return cOpt;
        }

        private static int buscarOptimo(int[][] cs, int i, int e, int a, int d, int[] solParc, int c, int[] solOpt, int cOpt){
            for (int k = 0; k <= e; k++){//for utilizado para recorrer cada una de las ramas
                if (k < d){ //comprobamos la condición de validez
                    solParc[i] = cs[k][i]; //actualizamos la solución parcial
                    int nd = d - k - 1; //nueva cantidad de días que nos quedan (condición de validez)
                    int nc = c + cs[k][i]; //suma de la nueva calificación obtenida (función objetivo)
                    if (i == a - 1){ //comprobamos si la solución construida es un nodo hoja
                        if (nc > cOpt){// comprobamos si es una solución óptima
                            cOpt = nc; //actualizamos
                            for (int j = 0; j < a; j++)
                                solOpt[j] = solParc[j]; //actualizamos la solución óptima
                        }
                    }
                    else
                        cOpt = buscarOptimo(cs, i + 1, e, a, nd, solParc, nc, solOpt, cOpt);
                }
            }
            return cOpt;
        }

        public static int calificacionesRyP(int[][] cs){

            //declaración de variables

            int a = cs[0].length;
            int f = cs.length;
            int d = a + f - 1;
            int e = d - a;

            int[] solParcial = new int[a];
            int[] solOptima = new int[a];

            int cota = 0;
            int max = 0;
            int vectorIndicesMaximos [] = new int [a];

            cota = getCota(cs, a, f, cota, max, vectorIndicesMaximos);

            int cOpt = buscarOptimoRyP(cs, 0, e, a, d, solParcial, 0, solOptima, -1, cota, vectorIndicesMaximos);
            imprimir(solOptima);

            return cOpt;
        }

    private static int getCota(int[][] cs, int a, int f, int cota, int max, int[] vectorIndicesMaximos) {
        for (int i = 0; i < a; i++){// por cada asignatura
            for (int j = 0; j < f; j++){//y en cada fila
                //buscamos el máximo
                if (cs[j][i] > max){
                    max = cs[j][i];
                    vectorIndicesMaximos[i] = j;//actualizamos el vector de indices
                }
            }
            cota += max;//calculamos el valor de la cota
            max = 0;
        }
        return cota;
    }

    private static int buscarOptimoRyP(int[][] cs, int i, int e, int a, int d, int[] solParc, int c, int[] solOpt, int cOpt, int cota, int[] vIndex){
            for (int k = 0; k <= e; k++){//for utilizado para recorrer cada una de las ramas
                if (k < d){ //comprobamos la condición de validez
                    solParc[i] = cs[k][i]; //actualizamos la solución parcial
                    int nd = d - k - 1; //nueva cantidad de días que nos quedan (condición de validez)
                    int nc = c + cs[k][i]; //suma de la nueva calificación obtenida (función objetivo)
                    int nCota = cota - cs[vIndex[i]][i]+ cs[k][i];
                    if (nCota > cOpt){ // seguimos computando soluciones si no se supera la cota
                        if (i == a - 1){ //comprobamos si la solución construida es un nodo hoja
                            if (nc > cOpt){// comprobamos si es una solución óptima
                                cOpt = nc; //actualizamos
                                for (int j = 0; j < a; j++)
                                    solOpt[j] = solParc[j]; //actualizamos la solución óptima
                            }
                        }
                        else
                            cOpt = buscarOptimoRyP(cs, i + 1, e, a, nd, solParc, nc, solOpt, cOpt, nCota, vIndex);
                    }
                }
            }
            return cOpt;
        }

        public static void imprimir(int[] calificaciones){
            for (int i = 0; i < calificaciones.length; i++){
                System.out.print(calificaciones[i] + " ");
            }
        }

        public static void main(String[] args) {
            //int[][] cs = {{4,3,5,2}, {4,5,6,4}, {5,6,8,7}};
            int[][] cs = {{9,2,5}, {4,2,8}, {1,3,3}};
            calificacionesRyP(cs);
        }
}
