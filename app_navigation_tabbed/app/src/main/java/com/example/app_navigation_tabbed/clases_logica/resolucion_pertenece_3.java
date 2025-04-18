package com.example.app_navigation_tabbed.clases_logica;

import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_direccionamiento_ip;
import com.example.app_navigation_tabbed.clases_logica.subredes.clase_subred;

public class resolucion_pertenece_3 {
    public resolucion_pertenece_3() {
    }

    
    
    
    
    public static String subred_al_que_pertenece(int ip_1, int ip_2, int ip_3, int ip_4, int prefijo) {
        long posicion;
        //RESOLUCION
        String resultado = "";

        //hallando la posicion
        clase_subred hayar_3 = new clase_subred();
        posicion = hayar_3.numero_de_subred_al_que_pertenece_una_ip(ip_1, ip_2, ip_3, ip_4, prefijo);

        //leemos datos de la red orignal
        clase_direccionamiento_ip clase_direccionamiento = new clase_direccionamiento_ip(ip_1, ip_2, ip_3, ip_4, prefijo);
        String red_original = clase_direccionamiento.get_id_red_decimal();
        String clase_red_original = clase_direccionamiento.get_clase_de_red();
        String limite_inferior_original = clase_direccionamiento.get_limiteInf_decimal();
        String limte_superior_original = clase_direccionamiento.get_limiteSup_decimal();

        // mostrar al usuario
        String ip = ip_1 + "." + ip_2 + "." + ip_3 + "." + ip_4 + "/" + prefijo;
        resultado = "la ip: " + ip + " pertenece a la subred N°: " + posicion + "\n" +
                " de la red original: " + red_original + " de clase: " + clase_red_original + "\n" +
                "con un limite indeferior de: " + limite_inferior_original + "\n" +
                "y un limite  de: " + limte_superior_original;


        return resultado;
    }
}
