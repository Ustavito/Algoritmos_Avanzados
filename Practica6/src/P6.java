public class P6 {

    //Primer algoritmo aproximado

    public static int menosCajasAprox1 (int[] ps, int c) {
        int[] is = new int[ps.length];
        is = ordenarIndicesCrec (ps);
        // cajas[i] contiene el índice del último objeto introducido en la caja i
        int[] cajas = new int[ps.length];
        // empezamos con 1 caja, pero lo inicializamos en uno menos para indexar arrays
        int num = 0;
        int p = c;
        for (int i=0; i<ps.length; i++) {
            if (ps[is[i]]<=p)
                p -= ps[is[i]];
            else {
                num++;
                p = c-ps[is[i]];
            }
            cajas[num] = i;
        }
        imprimirSol (cajas, num);
        return num+1;
    }

    private static int[] ordenarIndicesCrec (int[] v1) {
        // se ordena por inserción directa
        int[] v2 = new int[v1.length];
        v2[0] = 0;
        for (int i=1; i<v1.length; i++) {
            int aux = v1[i];
            int j;
            for (j=i-1; j>=0 && v1[v2[j]]>aux; j--)
                v2[j+1] = v2[j];
            v2[j+1] = i;
        }
        return v2;
    }

    private static void imprimirSol (int[] v, int x) {
        for (int i=0; i<=x; i++) {
            System.out.println ("Caja núm. "+i+" contiene los objetos entre "+(i==0?0:v[i-1]+1)+" y "+v[i]);
        }
    }

    // Segundo algoritmo aproximado

    public static int menosCajasAprox2 (int[] ps, int c) {
        int[] is = new int[ps.length];
        is = ordenarIndicesDecrec (ps);
        // cajas[i] contiene el peso que hay libre en cada caja
        int[] cajas = new int[ps.length];
        int num = 0;
        cajas[num] = c-ps[is[0]];
        for (int i=1; i<ps.length; i++) {
            boolean metido = false;
            for (int j=0; (j<=num) && !metido; j++) {
                if (ps[is[i]]<=cajas[j]) {
                    metido = true;
                    cajas[j] -= ps[is[i]];
                }
            }
            if (!metido) {
                num++;
                cajas[num] = c-ps[is[i]];
            }
        }
        return num+1;
    }

    private static int[] ordenarIndicesDecrec (int[] v1) {
        // se ordena por inserción directa
        int[] v2 = new int[v1.length];
        v2[0] = 0;
        for (int i=1; i<v1.length; i++) {
            int aux = v1[i];
            int j;
            for (j=i-1; j>=0 && v1[v2[j]]<aux; j--)
                v2[j+1] = v2[j];
            v2[j+1] = i;
        }
        return v2;
    }

    // Algoritmo de backtracking

    public static int menosCajasBack (int[] ps, int c) {
        int[] cajas = new int[ps.length];
        cajas[0] = c-ps[0];
        for (int i=1; i<ps.length; i++)
            cajas[i] = c;
        int[] cajasOpt = new int[ps.length];
        int num = buscarCajas (ps, c, 1, 1, cajas, ps.length, cajasOpt);
        return num;
    }

    private static int buscarCajas (int[] ps, int c,
                                    int i,
                                    int n, int[] cajas,
                                    int nOpt, int[] cajasOpt) {
        // versión basada en el esquema de la técnica de vuelta atrás para solución óptima
        for (int j=1; j<=n+1; j++)
            if (ps[i]<=cajas[j-1]) {
                cajas[j-1] -= ps[i];
                int nn = Math.max(n,j);
                if (i==ps.length-1) {
                    if (n<nOpt) {
                        nOpt = nn;
                        for (int k=0; k<nn; k++)
                            cajasOpt[k] = cajas[k];
                    }
                }
                else
                    nOpt = buscarCajas (ps, c, i+1, nn, cajas, nOpt, cajasOpt);
                cajas [j-1] += ps[i];
            }
        return nOpt;
    }

    // Algoritmo probabilista

    public static int menosCajasProb(int[] ps, int c) {
        int[] cajas = new int[ps.length]; // array que contiene los pesos restantes en cada caja
        /* Estado inicial */
        cajas[0] = c - ps[0]; // partimos de que metemos el primer objeto en la primera caja
        int n = 1; // partimos de que solo hemos usado una caja
        for (int i = 1; i < ps.length; i++){ //partimos de que las restantes cajas tienen máxima capacidad
            cajas[i] = c;
        }
        int j = 0;
        int posicionAInsertar;
        /* Algoritmo probabilista */
        for (int i = 1; i < ps.length; i++){
             while (j > 0 && ps[i] > cajas[j]){
                 j--;
             }
             if (ps[i] <= cajas[j]){// podemos insertar el objeto en alguna caja
                 do{
                     posicionAInsertar = (int) (Math.random() * (j + 1));
                 }while(cajas[posicionAInsertar] - ps[i] < 0);
                 j = posicionAInsertar;
             }
             else{ //tenemos que crear una nueva caja
                 j = n;
                 n++; //se incrementa el número de cajas
             }
             cajas[j] -= ps[i];
             j = n - 1;
        }
        return n;
    }

    public static void main(String[] args) {
        /*
        int[] vector = {2, 3, 6, 7, 1, 1};
        System.out.println(menosCajasProb(vector, 10));
        int[] vector2 = {2, 3, 6, 7, 1, 1};
        System.out.println(menosCajasProb(vector2, 7));
        int[] vector3 = {2, 3, 4, 5};
        int result = menosCajasProb(vector3, 7);
        System.out.println(result);
        System.out.println(menosCajasBack(vector3, 7));
         */

        int [] vector = {5, 2, 4, 1, 8, 2};
        int resultado = menosCajasProb(vector, 10);
        int resultado2 = menosCajasBack(vector, 10);
        System.out.println(resultado);
        System.out.println(resultado2);

    }
}
