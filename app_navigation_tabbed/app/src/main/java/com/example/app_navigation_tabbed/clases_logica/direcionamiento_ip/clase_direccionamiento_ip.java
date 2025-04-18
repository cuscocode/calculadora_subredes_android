package com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip;

import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_binario_y_decimal.*;

/**
 * @author raul hacho cutipa
 */
public class clase_direccionamiento_ip {

    // clase_binario_y_decimal clase_decimal_binario = new clase_binario_y_decimal();
    // clase_binario_y_decimal clase_decimal_binario = new clase_binario_y_decimal();
    private int ip1, ip2, ip3, ip4, prefijo;
    private int mas1, mas2, mas3, mas4;
    private int bro1, bro2, bro3, bro4;
    private int nro_maquinas,nro_subres;
    //mascaras
    private int arreglo_mascara[] = new int[32];
    private int arreglo_idred[] = new int[32];
    private int arreglo_gateway[] = new int[32];
    private int arreglo_broadcast[] = new int[32];
    private int arreglo_limite_inferior[] = new int[32];
    private int arreglo_limite_superior[] = new int[32];
    private int arregloIP[] = new int[32];
    String ipes;

    //-------------
    private String mascara, id_de_red, gateway, broacats, limiteInferior, limiteSuperior, clase;
    private int id1, id2, id3, id4;

    public clase_direccionamiento_ip() {
        ip1 = 0;
        ip2 = 0;
        ip3 = 0;
        ip4 = 0;
        mas1 = 0;
        mas2 = 0;
        mas3 = 0;
        mas4 = 0;
        id1 = 0;
        id2 = 0;
        id3 = 0;
        id4 = 0;
        bro1 = 0;
        bro2 = 0;
        bro3 = 0;
        bro4 = 0;
        prefijo = 0;
        mascara = "";
        id_de_red = "";
        gateway = "";
        broacats = "";
        limiteInferior = "";
        limiteSuperior = "";
        clase = "";
        ipes = "";
    }

    public clase_direccionamiento_ip(int iip1, int iip2, int iip3, int iip4, int pprefijo) {
        ip1 = iip1;
        ip2 = iip2;
        ip3 = iip3;
        ip4 = iip4;
        prefijo = pprefijo;
    }

    //mascara.............................................................---.----------------------------
    private void mascaraAux() {
        mas1 = 0;
        mas2 = 0;
        mas3 = 0;
        mas4 = 0;
        mascara = "";
        //llenamos la mascara de ceros
        for (int a = 0; a < 32; a++) {
            arreglo_mascara[a] = 0;
        }
        //reemplazanos los ceros por un 1 segun el prefijo
        for (int b = 0; b < prefijo; b++) {
            arreglo_mascara[b] = 1;
        }

        //enviamos el el arreglo con 32 bit y recimbimos cobertido a decimal String
        //resultado en decimal
        mascara = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_mascara);
    }

    //comvertimos la ip a binario
    private void ipaux() {

//recibimos la ip en arreglos convetidos a binario
        int arregloip1[] = new int[8];
        int arregloip2[] = new int[8];
        int arregloip3[] = new int[8];
        int arregloip4[] = new int[8];

        clase_binario_y_decimal clase_1 = new clase_binario_y_decimal(); //1
        arregloip1 = clase_1.get_arreglo_decimal_a_binario(ip1);

        clase_binario_y_decimal clase_2 = new clase_binario_y_decimal(); //2
        arregloip2 = clase_2.get_arreglo_decimal_a_binario(ip2);

        clase_binario_y_decimal clase_3 = new clase_binario_y_decimal();//3
        arregloip3 = clase_3.get_arreglo_decimal_a_binario(ip3);

        clase_binario_y_decimal clase_4 = new clase_binario_y_decimal();//4
        arregloip4 = clase_4.get_arreglo_decimal_a_binario(ip4);
        //llena en uno solo
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
        mascaraAux();
        ipaux();
        id_de_red = "";
        id1 = 0;
        id2 = 0;
        id3 = 0;
        id4 = 0;

        //llenamos el arreglo (id de red ) con ceros
        for (int i = 0; i < 32; i++) {
            arreglo_idred[i] = 0;
        }

        //costruimos el id de red
        for (int q = 0; q < 32; q++) {
            if (arreglo_mascara[q] == 1 && arregloIP[q] == 1) {
                arreglo_idred[q] = 1;
            } else {
                arreglo_idred[q] = 0;
            }
        }

        //resultado
        id_de_red = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_idred);
    }

    private void gatewayAux() {
        idredAux();
        arreglo_gateway = arreglo_idred;
        arreglo_gateway[31] = 1;
        gateway = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_gateway);

    }

    private void broakcastAux() {
        mascaraAux();
        idredAux();
        broacats = "";

        //calcular el bracast
        /*   for (int i = prefijo; i < 32; i++) {
            if (arreglo_mascara[i] == 1) {
                arreglo_broadcast[i] = arreglo_idred[i];
            } else {
                arreglo_broadcast[i] = 1;
            }*/
        arreglo_broadcast = arreglo_idred;
        for (int i = prefijo; i < 32; i++) {
            arreglo_broadcast[i] = 1;
        }
        broacats = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_broadcast);
    }

    private void limite_inferior() {
        gatewayAux();
        arreglo_limite_inferior = arreglo_gateway;
        arreglo_limite_inferior[31] = 0;
        arreglo_limite_inferior[30] = 1;
        limiteInferior = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_limite_inferior);
    }

    private void limite_superior() {
        broakcastAux();
        arreglo_limite_superior = arreglo_broadcast;
        arreglo_limite_superior[31] = 0;

        limiteSuperior = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arreglo_limite_superior);
    }
    private void numero_de_maquinas() {
        nro_maquinas = (int) Math.pow(2, 32 - prefijo)-2;
    }

    private void numero_De_subredes() {
        nro_subres = 0;
        if (get_clase_de_red().equals("A") && prefijo > 8) {
            nro_subres = (int) Math.pow(2, prefijo - 8) - 2;
        }
        if (get_clase_de_red().equals("B") && prefijo > 16) {
            nro_subres = (int) Math.pow(2, prefijo - 16) - 2;
        }
        if (get_clase_de_red().equals("C") && prefijo > 24) {
            nro_subres = (int) Math.pow(2, prefijo - 24) - 2;
        }
    }
    //=======================================================================

    public String get_numero_de_maquinas() {
        numero_de_maquinas();
        return "NUMERO DE HOST DISPONIBLES: " + nro_maquinas;
    }
    public String get_numero_de_subredes() {
        numero_De_subredes();
        if (nro_subres!=0) {
            return "NUMERO DE SUBREDES DISPONIBLES: " +nro_subres;
        }else{
            return "";
        }
    }

    public String get_clase_de_red() {
        clase = "";
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

    public String get_mascara_decimal() {
        mascaraAux();

        return mascara;
    }

    public String get_id_red_decimal() {
        idredAux();
        return id_de_red;
    }

    public String get_gateway_decimal() {
        gatewayAux();
        return gateway;
    }

    public String get_broadcats_decimal() {
        broakcastAux();
        return broacats;
    }

    public String get_limiteInf_decimal() {
        limite_inferior();
        return limiteInferior;
    }

    public String get_limiteSup_decimal() {
        limite_superior();
        return limiteSuperior;
    }

    public String get_ipes_decimal() {
        ipaux();

        return clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(arregloIP) + "/" + prefijo;
    }

    public String get_ipes_binario() {
        ipaux();

        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arregloIP);
    }

    public String getId_red_binario() {
        idredAux();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_idred);
    }

    public String getGateway_binario() {
        gatewayAux();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_gateway);
    }

    public String getBroadcast_binario() {
        broakcastAux();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_broadcast);
    }

    public String getLimite_inf_binario() {
        limite_inferior();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_limite_inferior);
    }

    public String getLimite_super_binario() {
        limite_superior();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_limite_superior);
    }

    public String getMascara_binario() {
        mascaraAux();
        return clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(arreglo_mascara);
    }

    /*public String prueba() {
        ipaux();
        String a = "";
        for (int i = 0; i < 8; i++) {
            a = a + arregloip1[i];
        }

        a = a + "   /" + ip1;
        return a;
    }*/
}
