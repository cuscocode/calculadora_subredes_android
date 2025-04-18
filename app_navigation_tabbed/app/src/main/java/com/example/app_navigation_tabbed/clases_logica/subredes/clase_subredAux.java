package com.example.app_navigation_tabbed.clases_logica.subredes;

/**
 * @author raul hacho cutipa
 */
public class clase_subredAux {

    private int ip1, ip2, ip3, ip4, prefijo;
    int arreglo_mascara[] = new int[32];

    public clase_subredAux() {
        prefijo = 0;

    }

    public clase_subredAux(int ip1, int ip2, int ip3, int ip4, int prefijo) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
        this.ip4 = ip4;
        this.prefijo = prefijo;
    }


    //HALLAR LA CLASE DE RED
    public String get_clase_red() {
        String clase = "";
        if (ip1 >= 1 && ip1 <= 127) {
            clase = "A";

        }
        if (ip1 >= 128 && ip1 <= 191) {
            clase = "B";

        }
        if (ip1 >= 192 && ip1 <= 223) {
            clase = "C";

        }
        if (ip1 >= 224 && ip1 <= 239) {
            clase = "D";
        }
        if (ip1 >= 240 && ip1 <= 255) {
            clase = "E";
        }
        return clase;
    }


    //=================================HALLAR LA MASCARA MODIFICADA
    public int[] get_mascara_moficada() {

        for (int a = 0; a < 32; a++) {
            arreglo_mascara[a] = 0;
        }
        for (int b = 0; b < prefijo;
             b++) {
            arreglo_mascara[b] = 1;
        }
        return arreglo_mascara;

    }


}
