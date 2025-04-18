package com.example.app_navigation_tabbed.clases_logica.Encontar_host;

import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_binario_y_decimal;
import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_direccionamiento_ip;

public class host_pertenece {

    private String ipes, id_red;
    private long host_pretenece;
    private int ip1, ip2, ip3, ip4, prefijo;
    private int arregloIP[] = new int[32];

    public host_pertenece(int ipp1, int ipp2, int ipp3, int ipp4, int pprefijo) {
        ip1 = ipp1;
        ip2 = ipp2;
        ip3 = ipp3;
        ip4 = ipp4;
        prefijo = pprefijo;
    }

    private void ipaux() {

//recibimos la ip en arreglos convetidos a binario
        int arregloip1[] = new int[8];
        int arregloip2[] = new int[8];
        int arregloip3[] = new int[8];
        int arregloip4[] = new int[8];

        clase_binario_y_decimal ip_prefijo = new clase_binario_y_decimal(); //1
        arregloip1 = ip_prefijo.get_arreglo_decimal_a_binario(ip1);

        clase_binario_y_decimal ip_prefijo2 = new clase_binario_y_decimal(); //2
        arregloip2 = ip_prefijo2.get_arreglo_decimal_a_binario(ip2);

        clase_binario_y_decimal ip_prefijo3 = new clase_binario_y_decimal();//3
        arregloip3 = ip_prefijo3.get_arreglo_decimal_a_binario(ip3);

        clase_binario_y_decimal ip_prefijo4 = new clase_binario_y_decimal();//4
        arregloip4 = ip_prefijo4.get_arreglo_decimal_a_binario(ip4);
        //llena todo en uno solo
        for (int g = 0; g < 8; g++) {
            arregloIP[g] = arregloip1[g];
        }
        int b = 0;
        for (int a = 8; a < 16; a++) {
            arregloIP[a] = arregloip2[b];
            b++;
        }
        int c = 0;
        for (int i = 16; i < 24; i++) {
            arregloIP[i] = arregloip3[c];
            c++;
        }
        int d = 0;
        for (int k = 24; k < 32; k++) {
            arregloIP[k] = arregloip4[d];
            d++;
        }
//---------------------aaa decimal
        int ipe1 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arregloip1);
        int ipe2 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arregloip2);
        int ipe3 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arregloip3);
        int ipe4 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arregloip4);
        //resultado
        ipes = ipe1 + "." + ipe2 + "." + ipe3 + "." + ipe4 + " /" + prefijo;

    }

    private void idredAux() {
        clase_direccionamiento_ip clase = new clase_direccionamiento_ip(ip1, ip2, ip3, ip4, prefijo);

        id_red = clase.get_id_red_decimal();
    }

    private void hallar_host() {
        ipaux();
        //hallar el numero de host al que pertenece la ip
        int exponente = 0, formula = 0;
        host_pretenece = 0;
        for (int i = arregloIP.length; i > prefijo; i--) {
            if (arregloIP[i-1] == 1) {
                formula = (int) Math.pow(2, exponente);
                host_pretenece = host_pretenece + formula;
            }

            exponente++;
        }
    }

    public String get_ipes() {
        ipaux();
        return ipes + "";
    }

    public String get_host() {
        hallar_host();
        return host_pretenece + "";
    }

    public String get_id_red() {
        idredAux();
        return id_red + "";
    }
}

