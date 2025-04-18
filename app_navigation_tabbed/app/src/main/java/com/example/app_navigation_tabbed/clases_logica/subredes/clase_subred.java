package com.example.app_navigation_tabbed.clases_logica.subredes;

import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_binario_y_decimal;


/**
 * @author raul hacho cutipa
 */
public class clase_subred {


    private String formula_a_usar = "";
    private int exp_formula, expt_salto, expt_maquina, expt_subredes;

    private int ip1, ip2, ip3, ip4, prefijo, Nsubred, resul_formula, numero_de_subredes, numero_de_maquinas, salto, octeto_de_salto, prefijoEntra;
    int mascara_modificada[] = new int[32];

    private String id_de_subred_enviar, gateway_enviar, broacast_enviar, limite_inferior_enviar, limite_superior_enviar;

    int valores_id[] = new int[32];
    int gateway[] = new int[32];
    int broacast[] = new int[32];
    int limite_inferior[] = new int[32];
    int limite_superior[] = new int[32];
    int arreglo_ip_binario[] = new int[32];

    int ip_1[] = new int[8];
    int ip_2[] = new int[8];
    int ip_3[] = new int[8];
    int ip_4[] = new int[8];

    int ip_11[] = new int[8];
    int ip_22[] = new int[8];
    int ip_33[] = new int[8];
    int ip_44[] = new int[8];

    private String mascara;

    public clase_subred() {
        ip1 = 0;
        ip2 = 0;
        ip3 = 0;
        ip4 = 0;
        exp_formula = 0;
        mascara = "";
        numero_de_maquinas = 0;
        numero_de_subredes = 0;
        salto = 0;
        octeto_de_salto = 0;
        id_de_subred_enviar = "";
        gateway_enviar = "";
        broacast_enviar = "";
        limite_inferior_enviar = "";
        limite_superior_enviar = "";

    }

    //constructor cuando va a ingresar un prefijo
    public clase_subred(int iip1, int iip2, int iip3, int iip4, int pprefijo) {
        ip1 = iip1;
        ip2 = iip2;
        ip3 = iip3;
        ip4 = iip4;
        prefijoEntra = pprefijo;

    }

    //constructor cuando solo se quiere dividir la red
    public clase_subred(int iip1, int iip2, int iip3, int iip4, int pprefijo, int nNsubred, String pformula) {
        ip1 = iip1;
        ip2 = iip2;
        ip3 = iip3;
        ip4 = iip4;
        prefijoEntra = pprefijo;
        Nsubred = nNsubred;
        formula_a_usar = pformula;
    }

    //HALLAR EL PREFIJO PARA LA MASCARA DE ACUERDO A LA FORMULA
    private void hallar_prefijo() {
        resul_formula = 0;

        //hallar el prefijo dependiendo a la clase en caso de que no pongan uno
        int prefijoAux = 0;
        if (prefijoEntra == 0) {
            if (ip1 >= 1 && ip1 <= 127) {
                prefijoAux = 8;
            }

            if (ip1 >= 128 && ip1 <= 191) {
                prefijoAux = 16;
            }

            if (ip1 >= 192 && ip1 <= 223) {
                prefijoAux = 24;
            }
            ///===================================

            //hallar la formula de acuerdo a la formula que escojera el usuario
            int n, a, i; //variables auxiliares
            if (formula_a_usar.equals("2^n-2")) {
                exp_formula = 0;
                resul_formula = 0;
                n = 1;
                a = 0;
                i = Nsubred;
                while (i > resul_formula) {
                    resul_formula = (int) (Math.pow(2, n)) - 2;
                    a++;
                    n++;
                    exp_formula = a;
                }
                prefijo = prefijoAux + exp_formula;
            }

            if (formula_a_usar.equals("2^n")) {
                exp_formula = 0;
                resul_formula = 0;
                n = 1;
                a = 0;
                i = Nsubred;
                while (i > resul_formula) {
                    resul_formula = (int) (Math.pow(2, n));
                    a++;
                    n++;
                    exp_formula = a;
                }

                prefijo = prefijoAux + exp_formula;
            }

        } else {
            prefijo = prefijoEntra;
        }

    }

    private void hallar_datos() {
        hallar_prefijo();
        //HALLAR LA NUEVA MASCARA MODIFICADA LLENANDO DE 1 DE ACUARDO AL PREFIJO
        clase_subredAux hayar = new clase_subredAux(ip1, ip2, ip3, ip4, prefijo);
        mascara_modificada = hayar.get_mascara_moficada();

        //enviamos el arreglo mascara y recibimos decimales para el usuario
        mascara = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(mascara_modificada);

        //HALLAR NUMEO DE MAQUINAS
        numero_de_maquinas = (int) Math.pow(2, 32 - prefijo) - 2;
        expt_maquina = 32 - prefijo;

        //HALLAR EL NUMERO DE SUBREDES POSIBLES=================================================
        if (ip1 >= 1 && ip1 <= 127) {
            numero_de_subredes = (int) Math.pow(2, prefijo - 8) - 2;
            expt_subredes = prefijo - 8;
        }
        if (ip1 >= 128 && ip1 <= 191) {
            numero_de_subredes = (int) Math.pow(2, prefijo - 16) - 2;
            expt_subredes = prefijo - 16;
        }
        if (ip1 >= 192 && ip1 <= 223) {
            numero_de_subredes = (int) Math.pow(2, prefijo - 24) - 2;
            expt_subredes = prefijo - 24;
        }

        //==============================================================

        //hayar salto
        // hallar_prefijo();

        if (prefijo > 8 && prefijo <= 16) {
            salto = (int) Math.pow(2, (32 - prefijo) - 16);
            octeto_de_salto = 2;
            expt_salto = (32 - prefijo) - 16;
        }
        if (prefijo > 16 && prefijo <= 24) {
            salto = (int) Math.pow(2, (32 - prefijo) - 8);
            octeto_de_salto = 3;
            expt_salto = (32 - prefijo) - 8;
        }
        if (prefijo > 24 && prefijo <= 32) {
            salto = (int) Math.pow(2, 32 - prefijo);
            octeto_de_salto = 4;
            exp_formula = 32 - prefijo;
        }

    }

    private void llenar_valores_al_arreglo() {
        clase_binario_y_decimal hayar = new clase_binario_y_decimal();

        //TRANSFORMAMOS LA IP A BINARIO PARA HALLAR LAS SUBREDES
        ip_1 = hayar.get_arreglo_decimal_a_binario(ip1);
        clase_binario_y_decimal hayar_2 = new clase_binario_y_decimal();
        ip_2 = hayar_2.get_arreglo_decimal_a_binario(ip2);
        clase_binario_y_decimal hayar_3 = new clase_binario_y_decimal();
        ip_3 = hayar_3.get_arreglo_decimal_a_binario(ip3);
        clase_binario_y_decimal hayar_4 = new clase_binario_y_decimal();
        ip_4 = hayar_4.get_arreglo_decimal_a_binario(ip4);

        //llenar  a todos los valores de 0
        for (int i = 0; i < 32; i++) {
            valores_id[i] = 0;
            gateway[i] = 0;
            broacast[i] = 0;
            limite_inferior[i] = 0;
            limite_superior[i] = 0;

        }
        //======================
        //llenado a los valores con la ip binario

        for (int i = 0; i < 8; i++) {
            valores_id[i] = ip_1[i];
            gateway[i] = ip_1[i];
            broacast[i] = ip_1[i];
            limite_inferior[i] = ip_1[i];
            limite_superior[i] = ip_1[i];
        }

        int a = 0;
        for (int i = 8; i < 16; i++) {
            valores_id[i] = ip_2[a];
            valores_id[i] = ip_2[a];
            gateway[i] = ip_2[a];
            broacast[i] = ip_2[a];
            limite_inferior[i] = ip_2[a];
            limite_superior[i] = ip_2[a];
            a++;
        }
        int b = 0;
        for (int i = 16; i < 24; i++) {
            valores_id[i] = ip_3[b];
            valores_id[i] = ip_3[b];
            gateway[i] = ip_3[b];
            broacast[i] = ip_3[b];
            limite_inferior[i] = ip_3[b];
            limite_superior[i] = ip_3[b];
            b++;
        }
        int c = 0;
        for (int i = 24; i < 32; i++) {
            valores_id[i] = ip_4[c];
            valores_id[i] = ip_4[c];
            gateway[i] = ip_4[c];
            broacast[i] = ip_1[c];
            limite_inferior[i] = ip_4[c];
            limite_superior[i] = ip_4[c];
            c++;
        }
        //   ================= completar la id de red=====================
        //para clase A
        if (ip1 >= 1 && ip1 <= 127) {
            for (int i = 8; i < 32; i++) {
                valores_id[i] = 0;
                gateway[i] = 0;
                broacast[i] = 1;
                limite_inferior[i] = 0;
                limite_superior[i] = 1;
            }

            gateway[31] = 1;
            limite_inferior[30] = 1;
            limite_superior[31] = 0;
        }
        if (ip1 >= 128 && ip1 <= 191) {
            for (int i = 16; i < 32; i++) {
                valores_id[i] = 0;
                gateway[i] = 0;
                broacast[i] = 1;
                limite_inferior[i] = 0;
                limite_superior[i] = 1;
            }
            gateway[31] = 1;
            limite_inferior[30] = 1;
            limite_superior[31] = 0;

        }
        if (ip1 >= 192 && ip1 <= 223) {
            for (int i = 24; i < 32; i++) {
                valores_id[i] = 0;
                gateway[i] = 0;
                broacast[i] = 1;
                limite_inferior[i] = 0;
                limite_superior[i] = 1;
            }
            gateway[31] = 1;
            limite_inferior[30] = 1;
            limite_superior[31] = 0;
        }
    }

    private void hallar_valores_con_el_numero(int numero) {
        llenar_valores_al_arreglo();
        hallar_prefijo();

        //hayar tamaño para el arreglo del numero
        int tamaño = 0;
        if (ip1 >= 1 && ip1 <= 127) {
            tamaño = prefijo - 8;

        }
        if (ip1 >= 128 && ip1 <= 191) {
            tamaño = prefijo - 16;

        }
        if (ip1 >= 192 && ip1 <= 223) {
            tamaño = prefijo - 24;

        }

        //hallar una clase que convierta el numero a binario en un arreglo con tamaño igual al prefijo
        int binario_numero[] = new int[tamaño];
        clase_binario_y_decimal hayar = new clase_binario_y_decimal();
        binario_numero = hayar.get_decimal_A_Binario_numeros(numero, tamaño);

        //llenar a los arreglos con el numero binario para hallar todos los valores para la tabla
        //PARA LA A
        int a = 0;
        if (ip1 >= 1 && ip1 <= 127) {
            for (int i = 8; i < prefijo; i++) {
                // valores_id[i] = binario_numero[i];
                valores_id[i] = binario_numero[a];
                gateway[i] = binario_numero[a];
                broacast[i] = binario_numero[a];
                limite_inferior[i] = binario_numero[a];
                limite_superior[i] = binario_numero[a];
                a++;
            }
        }

        //PARA LA B
        int b = 0;
        if (ip1 >= 128 && ip1 <= 191) {
            for (int i = 16; i < prefijo; i++) {
                //  valores_id[i] = binario_numero[i];
                valores_id[i] = binario_numero[b];
                gateway[i] = binario_numero[b];
                broacast[i] = binario_numero[b];
                limite_inferior[i] = binario_numero[b];
                limite_superior[i] = binario_numero[b];
                b++;
            }

        }

        //PARA LA C
        int c = 0;
        if (ip1 >= 192 && ip1 <= 223) {
            for (int i = 24; i < prefijo; i++) {
                valores_id[i] = binario_numero[c];
                gateway[i] = binario_numero[c];
                broacast[i] = binario_numero[c];
                limite_inferior[i] = binario_numero[c];
                limite_superior[i] = binario_numero[c];
                c++;
            }

        }

        //convertir a decimal String de los arreglos que estan en binario para mostrarlo al usuario
        //  clase_binario_y_decimal hayar_decimal = new clase_binario_y_decimal();
        id_de_subred_enviar = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(valores_id);
        gateway_enviar = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(gateway);
        broacast_enviar = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(broacast);
        limite_inferior_enviar = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(limite_inferior);
        limite_superior_enviar = clase_binario_y_decimal.get_arreglo_binario32_a_decimalString(limite_superior);
    }

    public long numero_de_subred_al_que_pertenece_una_ip(int ipp1, int ipp2, int ipp3, int ipp4, int prefijo_su) {

        clase_binario_y_decimal hayar = new clase_binario_y_decimal();
        ip_11 = hayar.get_arreglo_decimal_a_binario(ipp1);
        clase_binario_y_decimal hayar_2 = new clase_binario_y_decimal();
        ip_22 = hayar_2.get_arreglo_decimal_a_binario(ipp2);
        clase_binario_y_decimal hayar_3 = new clase_binario_y_decimal();
        ip_33 = hayar_3.get_arreglo_decimal_a_binario(ipp3);
        clase_binario_y_decimal hayar_4 = new clase_binario_y_decimal();
        ip_44 = hayar_4.get_arreglo_decimal_a_binario(ipp4);

        //llenando al arreglo de 32 con las ip binarias
        for (int i = 0; i < 8; i++) {
            arreglo_ip_binario[i] = ip_11[i];
        }

        int a = 0;
        for (int i = 8; i < 16; i++) {
            arreglo_ip_binario[i] = ip_22[a];
            a++;
        }
        int b = 0;
        for (int i = 16; i < 24; i++) {
            arreglo_ip_binario[i] = ip_33[b];
            b++;
        }
        int c = 0;
        for (int i = 24; i < 32; i++) {
            arreglo_ip_binario[i] = ip_44[c];
            c++;
        }

        ///hayar
        long numero_subred;

        String aux = "";
        if (ipp1 >= 1 && ipp1 <= 127) {
            for (int i = 8; i < prefijo_su; i++) {
                aux = aux + arreglo_ip_binario[i];
            }
        }
        if (ipp1 >= 128 && ipp1 <= 191) {
            for (int i = 16; i < prefijo_su; i++) {
                aux = aux + arreglo_ip_binario[i];
            }
        }
        if (ipp1 >= 192 && ipp1 <= 223) {
            for (int i = 24; i < prefijo_su; i++) {
                aux = aux + arreglo_ip_binario[i];
            }
        }
        clase_binario_y_decimal hayar_deci = new clase_binario_y_decimal();
        numero_subred = hayar_deci.convertirBinarioADecimalManual(aux);

        return numero_subred;
    }


    //========================================================================================================================
    //===========================================================================================================================/==============
    //======================================================== ENVIAR DATOS PARA EL FORMULARIO==================================================================================


    public int get_nro_SubDeRedes_Disponibles() {
        hallar_prefijo();
        hallar_datos();
        return numero_de_subredes;   //exp_formula        (formula + 1) + " enpesando de la subred 0";
    }

    public int get_SubDeRedes_limite() {
        hallar_prefijo();
        hallar_datos();
        return numero_de_subredes + 2;   //exp_formula    numero_de_subredes    (formula + 1) + " enpesando de la subred 0";
    }

    public String get_mascara_modificada() {
        hallar_datos();
        return mascara;
    }

    public String get_mascara_modificada_binario() {
        hallar_datos();
        //enviamos los 32 bit de la mascara y nos debuelbe en binario para el usuario
        String mascara_binario = clase_binario_y_decimal.get_arreglo_binario32_a_binarioString(mascara_modificada);
        return mascara_binario;
    }

    public String get_prefijo() {

        hallar_prefijo();
        return Integer.toString(prefijo);
    }

    public String get_nro_maquinas() {
        hallar_datos();
        return Integer.toString(numero_de_maquinas);
    }

    public String get_nro_subredes() {
        hallar_datos();
        return Integer.toString(numero_de_subredes);
    }

    public String get_salto_obteto() {
        hallar_datos();
        String posicion = "";
        switch (octeto_de_salto) {
            case 2:
                posicion = "segundo octeto";
                break;
            case 3:
                posicion = "tercer octeto";
                break;
            case 4:
                posicion = "cuarto octeto";
                break;
        }
        return salto + " en el " + posicion;
    }

    //enviar valores para la tabla
    public String get_id_de_subred(int numero) {
        hallar_valores_con_el_numero(numero);
        return id_de_subred_enviar;
    }

    public String get_gateway(int numero) {
        hallar_valores_con_el_numero(numero);
        return gateway_enviar;
    }

    public String get_broacast(int numero) {
        hallar_valores_con_el_numero(numero);
        return broacast_enviar;
    }

    public String get_limite_inferior(int numero) {
        hallar_valores_con_el_numero(numero);
        return limite_inferior_enviar;
    }

    public String get_limite_superior(int numero) {
        hallar_valores_con_el_numero(numero);
        return limite_superior_enviar;
    }

    //==============================
    public String get_valores_id() {
        llenar_valores_al_arreglo();
        String ip = "";
        for (int i = 0; i < 32; i++) {
            ip = ip + valores_id[i];
        }
        return ip;
    }

    public String get_direccion_de_red_principal() {
        llenar_valores_al_arreglo();
        return ip1 + "." + ip2 + "." + ip3 + "." + ip4;
    }

    public String get_clase_red() {
        clase_subredAux hayar = new clase_subredAux(ip1, ip2, ip3, ip4, prefijo);
        return hayar.get_clase_red();
    }

    public String get_sudredes_divididas() {

        return Nsubred + "";
    }

    public int[] get_formula() {
        hallar_prefijo();
        hallar_datos();
        int form[] = {exp_formula, resul_formula, expt_salto, expt_maquina, expt_subredes};
        return form;
    }

    public int get_expomemte_formula() {
        hallar_prefijo();
        return exp_formula;
    }

    //### RETORNAR resul_formula Y exp_formula=N
    //RECUPERAR PREFIJO
}

